package com.alcatraz.webgrab.activities;
import android.os.*;
import android.support.v7.widget.*;
import android.widget.*;
import android.view.*;
import java.io.*;
import android.content.*;
import android.support.design.widget.*;
import com.alcatraz.support.v4.appcompat.AlertDialogUtil;
import android.view.View.*;
import java.util.*;
import com.alcatraz.webgrab.*;

public class Page extends ThemedActivity
{
	android.support.v7.widget.Toolbar tb;
	TextView et;
	public static String done="donehhd";
	FloatingActionButton fab;
	String process;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		tb=(android.support.v7.widget.Toolbar) findViewById(R.id.pageToolbar1);
		et=(TextView) findViewById(R.id.pageEditText1);
		fab=(FloatingActionButton) findViewById(R.id.fab);
		String ingg=Output.read(new File(Output.root+"cache.html"));
		process=ingg.substring(5,ingg.length());
		et.setText(process);
		tb.setBackgroundColor(getRgb());
		setupStaticColorPadding(getRgb());
		tb.setTitle(R.string.main_menu_5);
		setSupportActionBar(tb);
		fab.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					showgggd();
					// TODO: Implement this method
				}
			});
	}
	public void showgggd(){
		View v=getLayoutInflater().inflate(R.layout.download_single,null);
		final TextInputLayout til=(TextInputLayout) v.findViewById(R.id.textInputLayout12);
		til.getEditText().setText(Output.root);
		android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this)
		.setTitle(R.string.pre_pb)
		.setView(v)
		.setNegativeButton(R.string.pre_nb,null)
		.setPositiveButton(R.string.pre_pb, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					File f=new File(til.getEditText().getText().toString()+"download.html");
					File cache=new File(Output.root+"cache.html");
					try
					{
						if(!f.exists()){
							if(f.getParentFile()!=null){
								f.getParentFile().mkdirs();
							}
							f.createNewFile();
						}
						
						forJava(cache, f);
						Toast.makeText(Page.this, R.string.download_complete, Toast.LENGTH_SHORT).show();
					}
					catch (Exception e)
					{}
					
					// TODO: Implement this method
				}
			})
		.create();
		new AlertDialogUtil().setSupportDialogColor(a,getRgb());
		a.show();
	}
	public static long forJava(File f1,File f2) throws Exception{
		long time=new Date().getTime();
		int length=2097152;
		FileInputStream in=new FileInputStream(f1);
		FileOutputStream out=new FileOutputStream(f2);
		byte[] buffer=new byte[length];
		while(true){
			int ins=in.read(buffer);
			if(ins==-1){
				in.close();
				out.flush();
				out.close();
				return new Date().getTime()-time;
			}else
				out.write(buffer,0,ins);
		}
	}
}
