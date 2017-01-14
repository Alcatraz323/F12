package com.alcatraz.webgrab;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.support.v7.widget.*;
import android.support.v4.widget.*;
import android.support.design.widget.*;
import android.view.View.*;
import android.support.v7.app.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import android.util.*;

public class MainActivity extends ThemedActivity 
{
	View v;
	android.support.v7.widget.Toolbar tb;
	public static final String THEME_ACTION="t";
	DrawerLayout dl;
	ImageView imgvp;
	NavigationView ngv;
	UpdateThemeReceiver utr;
	FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initViews();
		regist();
		connectt();
    }
	public void initViews()
	{
		v = findViewById(R.id.mainView1);
		fab=(FloatingActionButton) findViewById(R.id.fab1);
		fab.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
				}
			});
		tb = (android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar1);
		ngv=(NavigationView) findViewById(R.id.navigation);
		imgvp=(ImageView) ngv.getHeaderView(0).findViewById(R.id.navheaderImageView1);
		if(rgb==getColor(R.color.nightmode_colorPrimary)){
			imgvp.setImageResource(R.drawable.image_1_01);
		}
		dl = (DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		tb.setTitle(R.string.app_name);
		setSupportActionBar(tb);
		setupMaterialWithDrawer(dl, tb, v);
	}
	public void connectt(){
		Thread t=new Thread(new Runnable(){

				@Override
				public void run()
				{
					try
					{
						URLConnection conn=new URL("http://coolapk.com").openConnection();
						conn.setRequestProperty("accept", "*/*");
						conn.setRequestProperty("connection", "Keep-Alive");
						conn.setDoOutput(true);
						conn.setDoInput(true);
						conn.setReadTimeout(2000);
						BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
						while(true){
							String line=reader.readLine();
							Log.e("Alc",line);
							if(line==null){
								break;
							}
						}
					}
					catch (Exception e)
					{}
					// TODO: Implement this method
				}
			});
			t.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mi=new MenuInflater(this);
		mi.inflate(R.menu.main_menu, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}
	public void regist(){
		IntentFilter ifil=new IntentFilter();
		ifil.addAction(THEME_ACTION);
		utr=new UpdateThemeReceiver();
		registerReceiver(utr,ifil);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.item1:
				startActivity(new Intent(this,Preferences.class));
				break;
			case R.id.item2:
				startActivity(new Intent(this,Author.class));
				break;
		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy()
	{
		unregisterReceiver(utr);
		// TODO: Implement this method
		super.onDestroy();
	}
	
	class UpdateThemeReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context p1, Intent p2)
		{
			finish();
			startActivity(new Intent(MainActivity.this,MainActivity.class));
			// TODO: Implement this method
		}
		
		
	}
}
