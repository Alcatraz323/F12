package com.alcatraz.webgrab.adapters;
import android.widget.*;
import android.view.*;
import android.widget.Filter.*;
import java.util.*;
import android.content.*;
import android.graphics.*;
import android.support.v7.widget.*;
import com.alcatraz.webgrab.*;

public class AutoAdapter extends BaseAdapter implements Filterable
{
	List<String> show=new ArrayList<String>();
	SharedPreferences spf;
	Context ctx;
	LayoutInflater lf;
	String theme;
	MFilter filte;
	String saved;
	List<String> fromsaved=new ArrayList<String>();
	Utils u;
	int rgb;
	public AutoAdapter(Utils u,Context ctx,int rgb,String theme){
		this.u=u;
		this.ctx=ctx;
		this.rgb=rgb;
		this.theme=theme;
		ctx.getSharedPreferences(ctx.getPackageName()+"_preferences",ctx.MODE_PRIVATE);
		lf=(LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
	}
	public void updateSaved(){
		saved=(String) u.getPreference(Utils.PreferenceType.STRING,"saved","");
		String[] pro=saved.split("_&-__");
		fromsaved.clear();
		for(String i:pro){
			fromsaved.add(i);
		}
	}
	@Override
	public int getCount()
	{
		try{
		// TODO: Implement this method
		return show.size();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public Object getItem(int p1)
	{
		String true_value=(String) show.toArray()[p1];
		String[] lro=true_value.split(":",2);
		// TODO: Implement this method
		return lro[1];
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
			p2=lf.inflate(R.layout.autocomplete,null);
		}
		if(theme!="night"){
			p2.setBackgroundColor(Color.parseColor("#ffffff"));
		}
		TextView label=(TextView) p2.findViewById(R.id.autocompleteTextView1);
		TextView urlshow=(TextView) p2.findViewById(R.id.autocompleteTextView2);
		String line=show.get(p1);
		String[] pro=line.split(":",2);
		label.setText(pro[0]);
		urlshow.setText(pro[1]);
		urlshow.setTextColor(rgb);
		// TODO: Implement this method
		return p2;
	}

	@Override
	public Filter getFilter()
	{
		// TODO: Implement this method
		return new MFilter();
	}
	class MFilter extends Filter
	{

		@Override
		protected Filter.FilterResults performFiltering(CharSequence p1)
		{
			FilterResults fr=new FilterResults();
			List<String> temp=new ArrayList<String>();
			for(String i:fromsaved){
				if(i.contains(p1)){
					temp.add(i);
				}
			}
			fr.values=temp;
			fr.count=temp.size();
			// TODO: Implement this method
			return fr;
		}

		@Override
		protected void publishResults(CharSequence p1, Filter.FilterResults p2)
		{
			show=(List<String>) p2.values;
			notifyDataSetChanged();
			// TODO: Implement this method
		}
		
		
	}
}
