package com.alcatraz.webgrab;
import org.jsoup.nodes.*;
import com.unnamed.b.atv.model.*;
import android.text.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.icu.util.*;
import java.util.*;
import android.view.View.*;
import android.util.*;
import com.alcatraz.webgrab.bean.*;
import android.support.v4.content.*;
import com.unnamed.b.atv.view.*;
import android.graphics.*;

public class TreeHolder extends TreeNode.BaseNodeViewHolder<Bean>
{
	TreeNode tn;
	LinearLayout ll;
	ImageView imgv;
	Context xtx;
	public TreeHolder(Context context)
	{
		super(context);
		xtx=context;
	}
	public void setHighLight(String rgb){
		ll.setBackgroundColor(Color.parseColor(rgb));
	}
	@Override
	public View createNodeView(final TreeNode node, Bean value)
	{
		node.srtT(this);
		LayoutInflater lf=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View root=lf.inflate(R.layout.treeline, null);
		imgv=(ImageView) root.findViewById(R.id.treelineImageView1);
		ll=(LinearLayout) root.findViewById(R.id.treelineLinearLayout1);
		ll.setPadding(Dp2Px(context, 16 * value.getPadding()), 0, 0, 0);
		TextView txv=(TextView) root.findViewById(R.id.codeview);
		txv.setText(dexorate(hasMyChild(value.getData()),value.getData())/*cutHeader(value.data.outerHtml())*/);
		if(!hasMyChild(value.getData())){
			imgv.setVisibility(View.GONE);
		}
		OnClickListener ol=new OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				if (!node.isExpanded())
				{
					getTreeView().toggleNode(node);
					imgv.setImageResource(R.drawable.ic_menu_down);
				}
				else
				{
					getTreeView().toggleNode(node);
					imgv.setImageResource(R.drawable.ic_menu_right);
				}
				//Toast.makeText(xtx,node.getPath(),Toast.LENGTH_SHORT).show();
				// TODO: Implement this method
			}
		};
		node.setClickListener(new TreeNode.TreeNodeClickListener(){

				@Override
				public void onClick(TreeNode node, Object value)
				{
					toggleArrow(node);
					// TODO: Implement this method
				}
			});
			txv.setOnClickListener(ol);
			imgv.setOnClickListener(ol);
		// TODO: Implement thips method
		return root;
	}
	class QueueB_Access extends BroadcastReceiver
	{
		TreeNode node;
		AndroidTreeView atv;
		ImageView imgv;
		public QueueB_Access(TreeNode tn,AndroidTreeView atv,ImageView iv){
			node=tn;
			this.atv=atv;
			imgv=iv;
		}
		@Override
		public void onReceive(Context p1, Intent p2)
		{
			if (!node.isExpanded())
			{
				atv.toggleNode(node);
				imgv.setImageResource(R.drawable.ic_menu_down);
			}
			else
			{
				atv.toggleNode(node);
				imgv.setImageResource(R.drawable.ic_menu_right);
			}
			// TODO: Implement this method
		}
		
		
	}
	public void toggleArrow(TreeNode node){
		if (!node.isExpanded())
		{
			imgv.setImageResource(R.drawable.ic_menu_down);
		}
		else
		{
			imgv.setImageResource(R.drawable.ic_menu_right);
		}
	}
	public int Dp2Px(Context context, float dp)
	{ 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int) (dp * scale + 0.5f); 
	} 
	public boolean hasMyChild(Node i)
	{
		List<Node> chi=i.childNodes();
		for (Node e:chi)
		{
			if ((!e.nodeName().contains("#")) || e.nodeName().contains("#comment")||e.nodeName().contains("#data"))
			{
				return true;
			}
		}
		return false;
	}
	public CharSequence dexorate(boolean h, Node nlde)
	{
		if(nlde.nodeName().equals("#comment")){
			String jd=nlde.outerHtml().trim();
			jd="<font color=\"#4caf50\">"+jd.replace("<","&lt;")+"</font>";
			return Html.fromHtml(jd);
		}
		if(nlde.nodeName().contains("data")){
			return nlde.outerHtml();
		}
		return matchTag(nlde,h);
		/*
		if(h){
			return _trimAttributesAndTag(cutHeader(nlde.outerHtml().trim().replace("<","&lt;")),nlde,h);
		}else{
			return _trimAttributesAndTag(nlde.outerHtml().trim().replace("<","&lt;"),nlde,h);
		}*/
		
	}
	public Spanned matchTag(Node i,boolean hasc){
		Attributes a_array=i.attributes();
		if(hasc){
			String raw="";
			raw+="&lt;"+"<font color='#9c27b0'>"+i.nodeName()+"</font>"+" ";
			int iter=0;
			for(Attribute a:a_array){
				if(iter<a_array.size()-1){
					raw+="<font color='#ffb300'>"+a.getKey()+"</font>"+"=\""+"<font color='#3f51b5'>"+a.getValue()+"</font>"+"\""+" ";
					
				}else{
					raw+="<font color='#ffb300'>"+a.getKey()+"</font>"+"=\""+"<font color='#3f51b5'>"+a.getValue()+"</font>"+"\""+"&gt";
				}
				iter++;
			}
			if(iter==0){
				raw+="&gt;";
			}
			return Html.fromHtml(raw);
		}else{
			String raw="";
			raw+="&lt;"+"<font color='#9c27b0'>"+i.nodeName()+"</font>"+" ";
			int iter=0;
			for(Attribute a:a_array){
				if(iter<a_array.size()-1){
					raw+="<font color='#ffb300'>"+a.getKey()+"</font>"+"=\""+"<font color='#3f51b5'>"+a.getValue()+"</font>"+"\""+" ";
				
				}else{
					raw+="<font color='#ffb300'>"+a.getKey()+"</font>"+"=\""+"<font color='#3f51b5'>"+a.getValue()+"</font>"+"\""+"&gt;";
				}
				iter++;
			}
			if(iter==0){
				raw+="&gt;";
			}
			List<Node> lis=i.childNodes();
			for(Node l:lis){
				raw+=l.outerHtml();
			}
			raw+="&lt;/"+"<font color='#9c27b0'>"+i.nodeName()+"</font>"+"&gt;";
			return Html.fromHtml(raw);
		}
	}
	public static String cutHeader(String source){
		String[] lrocess=source.split(">");
		String done=lrocess[0]+">";
		return done;
	}
	/*public Spanned _trimAttributesAndTag(String cut,Node i,boolean h){
		String orig=cut;
		Attributes ia=i.attributes();
		
		orig=orig.replace(i.nodeName(),"<font color='#9c27b0'>"+i.nodeName()+"</font>");
		orig=replaceLast(orig,i.nodeName(),"<font color='#9c27b0'>"+i.nodeName()+"</font>");
		if(!h){
		
		}
		for(Attribute a:ia){
			orig=orig.replace(a.getKey(),"<font color='#ffb300'>"+a.getKey()+"</font>");
			orig=orig.replace(a.getValue(),"<font color='#3f51b5'>"+a.getValue()+"</font>");
		}
		Log.e("Alc_repl",orig);
		return Html.fromHtml(orig);
	}*/
	public static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos)
				+ replacement
				+ string.substring(pos + toReplace.length(), string.length());
		} else {
			return string;
		}
	}
}


