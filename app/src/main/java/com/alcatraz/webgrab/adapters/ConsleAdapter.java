package com.alcatraz.webgrab.adapters;
import android.content.*;
import android.net.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.webkit.*;
import android.widget.*;
import com.alcatraz.webgrab.*;
import java.text.*;
import java.util.*;

public class ConsleAdapter extends BaseAdapter
{
	List<android.webkit.ConsoleMessage> data;
	Context c;
	LayoutInflater lf;
	public ConsleAdapter(Context c,List<ConsoleMessage> data){
		this.c=c;
		this.data=data;
		lf=(LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return data.size();
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return data.get(p1);
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public View getView(int p1, View p2, ViewGroup p3)
	{
		if(p2==null){
			p2=lf.inflate(R.layout.conslelog,null);
		}
		TextView txv=(TextView) p2.findViewById(R.id.conslelogTextView1);
		TextView line=(TextView) p2.findViewById(R.id.conslelogTextView2);
		TextView level=(TextView) p2.findViewById(R.id.conslelogTextView3);
		TextView time=(TextView) p2.findViewById(R.id.conslelogTextView4);
		final ConsoleMessage item=data.get(p1);
		txv.setText(item.message());
		txv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					android.content.ClipboardManager cmb = (android.content.ClipboardManager)c.getSystemService(Context.CLIPBOARD_SERVICE);  
					cmb.setText(item.message());
					Toast.makeText(c,R.string.copy,Toast.LENGTH_SHORT).show();
					// TODO: Implement this method
				}
			});
		Spanned j=Html.fromHtml("<a href=\""+item.sourceId()+"\">"+item.sourceId()+"</a>"+"(line:"+item.lineNumber()+")");
		line.setText(j);
		if(item.sourceId().contains("://")){
		line.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent intent = new Intent();        
					intent.setAction("android.intent.action.VIEW");    
					Uri content_url = Uri.parse(item.sourceId());   
					intent.setData(content_url);  
					c.startActivity(intent);
					// TODO: Implement this method
				}
			});
			}
		level.setText(type(item.messageLevel()));
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date(System.currentTimeMillis());
		time.setText(df.format(d));
		// TODO: Implement this method
		return p2;
	}
	public CharSequence type(ConsoleMessage.MessageLevel g){
		if(g.equals(ConsoleMessage.MessageLevel.DEBUG)){
			return Html.fromHtml("<font color=\"#2196F3\">"+"DEBUG"+"</font>");
		}else if(g.equals(ConsoleMessage.MessageLevel.ERROR)){
			return Html.fromHtml("<font color=\"#F44336\">"+"ERROR"+"</font>");
		}else if(g.equals(ConsoleMessage.MessageLevel.LOG)){
			return Html.fromHtml("<font color=\"#4CAF50\">"+"LOG"+"</font>");
		}else if(g.equals(ConsoleMessage.MessageLevel.TIP)){
			return "TIP";
		}else if(g.equals(ConsoleMessage.MessageLevel.WARNING)){
			return Html.fromHtml("<font color=\"#FF9800\">"+"WARNING"+"</font>");
		}
		return "TIP";
	}
}
