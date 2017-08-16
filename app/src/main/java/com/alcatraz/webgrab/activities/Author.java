package com.alcatraz.webgrab.activities;
import android.support.v7.app.*;
import android.os.*;
import java.util.*;
import android.content.pm.*;
import android.content.pm.PackageManager.*;
import android.widget.*;
import android.support.v7.widget.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.content.*;
import android.net.*;
import com.alcatraz.support.v4.appcompat.AlertDialogUtil;
import android.graphics.*;
import android.support.design.widget.*;
import android.text.*;
import com.alcatraz.webgrab.*;
import com.alcatraz.webgrab.adapters.*;

public class Author extends ThemedActivity
{
	List<Integer> imgs=new ArrayList<Integer>();
	Map<Integer,List<String>> data=new HashMap<Integer,List<String>>();
	ListView lv;
	AppBarLayout abl;
	android.support.v7.widget.Toolbar tb;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author);
		initData();
		initViews();
	}
	public void showDetailDev(){
		Spanned spd=Html.fromHtml("<br/>图标:busstop12<br/><br/><br/><h3>开放源代码许可</h3><p>-Jsoup</p><a href=\"https://github.com/jhy/jsoup/\">https://github.com/jhy/jsoup/</a><br/><p>License:</p><br/><p>Copyright © 2009 - 2016 Jonathan Hedley (jonathan@hedley.net)Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.</p><br/><h>AndroidTreeView</h></br><a href='https://github.com/bmelnychuk/AndroidTreeView/'>https://github.com/bmelnychuk/AndroidTreeView/</a></br><p>ApacheLicense</p></br><a href='https://github.com/bmelnychuk/AndroidTreeView/blob/master/LICENSE'>https://github.com/bmelnychuk/AndroidTreeView/blob/master/LICENSE</a>");
		android.support.v7.app.AlertDialog g=new android.support.v7.app.AlertDialog.Builder(this)
		.setTitle(R.string.au_l_2)
		.setMessage(spd)
		.setPositiveButton(R.string.ad_pb1,null)
		.create();
		new AlertDialogUtil().setSupportDialogColor(g,Color.parseColor("#3f51b5"));
		g.show();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initViews()
	{
		tb = (android.support.v7.widget.Toolbar) findViewById(R.id.mytoolbar_1);
		abl=(AppBarLayout) findViewById(R.id.appbar);
		abl.setBackgroundColor(getRgb());
		setupStaticColorPadding(getRgb());
		
		setSupportActionBar(tb);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		lv = (ListView) findViewById(R.id.authorcontentListView1);
		AuthorAdapter aa=new AuthorAdapter(this, data, imgs);
		lv.setAdapter(aa);
		lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if (p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_3)))
					{
						
					}else if (p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_4))){
						String str = "market://details?id=" + getPackageName();
						Intent localIntent = new Intent("android.intent.action.VIEW");
						localIntent.setData(Uri.parse(str));
						startActivity(localIntent);
					}else if(p1.getItemAtPosition(p3).toString().equals(getString(R.string.au_l_2))){
						showDetailDev();
					}
				}
				// TODO: Implement this metho
			});
	}
	public void initData()
	{
		imgs.add(R.drawable.ic_information_outline);
		imgs.add(R.drawable.ic_account);
		imgs.add(R.drawable.ic_github);
		imgs.add(R.drawable.ic_arrow_right);
		List<String> l1=new ArrayList<String>();
		l1.add(getString(R.string.au_l_1));
		l1.add("---");
		List<String> l2=new ArrayList<String>();
		l2.add(getString(R.string.au_l_2));
		l2.add(getString(R.string.au_l_2_1));
		List<String> l3=new ArrayList<String>();
		l3.add(getString(R.string.au_l_3));
		l3.add("");
		List<String> l4=new ArrayList<String>();
		l4.add(getString(R.string.au_l_4));
		l4.add(getString(R.string.au_l_4_1));
		data.put(0, l1);
		data.put(1, l2);
		data.put(2, l3);
		data.put(3, l4);

	}
}
