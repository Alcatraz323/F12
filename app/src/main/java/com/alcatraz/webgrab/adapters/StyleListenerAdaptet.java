package com.alcatraz.webgrab.adapters;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.alcatraz.webgrab.*;
import java.util.*;
import android.text.*;

public class StyleListenerAdaptet extends BaseAdapter
{
	List<String> data;
	LayoutInflater lf;
	public StyleListenerAdaptet(List<String> data,Context c){
		lf=(LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
		this.data=data;
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
			p2=lf.inflate(R.layout.style_listener_item,null);
		}
		TextView txv=(TextView) p2.findViewById(R.id.stylelisteneritemTextView1);
		txv.setText(data.get(p1));
		// TODO: Implement this method
		return p2;
	}
	public Spanned decorateCss(String source){
		String[] process_1=source.split("{");
		String secondary=process_1[1].substring(0,process_1[1].length()-1);
		return null;
	}
}
