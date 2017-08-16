package com.alcatraz.webgrab.adapters;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.alcatraz.webgrab.*;
import java.util.*;
import com.alcatraz.webgrab.bean.*;

public class ExpandableAdapter extends BaseExpandableListAdapter
{

	@Override
	public int getGroupCount()
	{
		// TODO: Implement this method
		return parent.size();
	}
	
	List<String> parent;
	Map<String,List<ResFiles>> data;
	Context ctx;
	LayoutInflater inflater;
	public ExpandableAdapter(Context context,List<String> parent,Map<String,List<ResFiles>> data){
		this.parent=parent;
		this.data=data;
		ctx=context;
		inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getChildrenCount(int p1)
	{
		// TODO: Implement this method
		String key = parent.get(p1);
		int size=data.get(key).size();
		return size;
	}

	@Override
	public Object getGroup(int p1)
	{
		// TODO: Implement this method
		return parent.get(p1);
	}

	@Override
	public Object getChild(int p1, int p2)
	{
		// TODO: Implement this method
		String key = parent.get(p1);
		return (data.get(key).get(p2));
	}

	@Override
	public long getGroupId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public long getChildId(int p1, int p2)
	{
		// TODO: Implement this method
		return p2;
	}

	@Override
	public boolean hasStableIds()
	{
		// TODO: Implement this method
		return true;
	}
	@Override
	public View getGroupView(int p1, boolean p2, View p3, ViewGroup p4)
	{
		if(p3==null){
			p3=inflater.inflate(R.layout.exp_parent,null);
		}
		TextView t=(TextView) p3.findViewById(R.id.expparentTextView1);
		t.setText(parent.get(p1));
		// TODO: Implement this method
		return p3;
	}

	@Override
	public View getChildView(int p1, int p2, boolean p3, View p4, ViewGroup p5)
	{
		if(p4==null){
			p4=inflater.inflate(R.layout.exp_child,null);
		}
		TextView txv=(TextView) p4.findViewById(R.id.expchildTextView1);
		ImageView imgv=(ImageView) p4.findViewById(R.id.expchildImageView1);
		ResFiles f=data.get(parent.get(p1)).get(p2);
		String fileName=f.getName();
		txv.setText(fileName);
		imgv.setImageResource(Analyser.getResIDbyFileClass(fileName));
		// TODO: Implement this method
		return p4;
	}

	@Override
	public boolean isChildSelectable(int p1, int p2)
	{
		// TODO: Implement this method
		return true;
	}
	
}
