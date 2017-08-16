package com.alcatraz.webgrab.activities;
import android.preference.*;
import android.os.*;
import com.alcatraz.support.v4.appcompat.StatusBarUtil;
import com.alcatraz.support.v4.appcompat.AlertDialogUtil;
import android.content.*;
import android.graphics.*;
import android.support.v7.widget.*;
import android.view.View.*;
import android.view.*;
import android.util.*;
import android.test.*;
import android.support.design.widget.*;
import android.text.*;
import android.net.*;
import com.alcatraz.webgrab.*;

public class Preferences extends PreferenceActivity
{
	OverallOperate app;
	Utils u;
	android.support.v7.widget.Toolbar tb;
	ListPreference list1;
	String theme;
	PreferenceScreen ps;
	int rgb;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		app = (OverallOperate) getApplication();
		u = app.getUtilInstance();
		updateThemeSettings();
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference_container);
		initViews();
		addPreferencesFromResource(R.layout.preference_content);
		findPreferences();
	}
	public void initViews()
	{
		tb = (Toolbar) findViewById(R.id.preferenceToolbar1);
		tb.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
					// TODO: Implement this method
				}
			});
		tb.setTitle(R.string.activity_pref);
		tb.setBackgroundColor(rgb);
	}
	public void findPreferences()
	{
		StatusBarUtil.setColor(this, rgb);
		ps=(PreferenceScreen) findPreference("hl");
		list1 = (ListPreference) findPreference("theme");
		list1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){

				@Override
				public boolean onPreferenceChange(Preference p1, Object p2)
				{
					if(!p2.equals("cus")){
					updateList();
					sendBroadcast(new Intent().setAction(MainActivity.THEME_ACTION));
					finish();
					startActivity(new Intent(Preferences.this, Preferences.class));
					// TODO: Implement this method
					return true;
					}else{
						View v=getLayoutInflater().inflate(R.layout.custom_al,null);
						final View v1=v.findViewById(R.id.customalView1);
						final TextInputLayout til=(TextInputLayout) v.findViewById(R.id.textInputLayout2);
						final android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(Preferences.this)
							.setTitle(R.string.pref_cus)
							.setView(v)
							.setNegativeButton(R.string.ad_nb1,null)
							.setPositiveButton(R.string.ad_pb1, new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface p1, int p2)
								{
									u.savePreference(Utils.PreferenceType.STRING,"theme","cus");
									u.savePreference(Utils.PreferenceType.STRING,"custom_rgb",til.getEditText().getText().toString());
									updateList();
									sendBroadcast(new Intent().setAction(MainActivity.THEME_ACTION));
									finish();
									startActivity(new Intent(Preferences.this, Preferences.class));
									// TODO: Implement this method
								}
							})
							.create();
						new AlertDialogUtil().setSupportDialogColor(a,rgb);
						
						a.show();
						a.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
						til.getEditText().addTextChangedListener(new TextWatcher(){

								@Override
								public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
								{
									// TODO: Implement this method
								}

								@Override
								public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
								{
									// TODO: Implement this method
								}

								@Override
								public void afterTextChanged(Editable p1)
								{
									try{
										v1.setBackgroundColor(Color.parseColor(til.getEditText().getText().toString()));
										a.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
										til.setErrorEnabled(false);
									}catch(Exception e){
										if(til.getEditText().getText().toString().length()>=7){
											til.setErrorEnabled(true);
											til.setError(getString(R.string.pref_cus_error));
										}
										a.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
									}
									// TODO: Implement this method
								}
							});
						return false;
						
					}
				}
			});
		ps.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference p1)
				{
					Intent i = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");

					String pkg = "com.android.settings";
					String cls = "com.android.settings.applications.InstalledAppDetails";

					i.setComponent(new ComponentName(pkg, cls));
					i.setData(Uri.parse("package:" + getPackageName()));
					startActivity(i);
					// TODO: Implement this method
					return true;
				}
			});
		updateList();
	}
	public void updateSummary(String key, String defaulth, Preference v)
	{
		v.setSummary(u.getPreference(Utils.PreferenceType.STRING, key, defaulth));
	}
	public void updateList()
	{
		list1.setSummary(matchThemeList());
	}
	public String matchThemeList()
	{
		String[] entry=getResources().getStringArray(R.array.entryvalues_list_preference);
		String[] show=getResources().getStringArray(R.array.entries_list_preference);
		for (int i=0;i <= entry.length;i++)
		{
			if (entry[i].equals(theme))
			{
				return show[i];
			}
		}
		return null;
	}
	public void updateThemeSettings()
	{
		theme = (String) u.getPreference(Utils.PreferenceType.STRING, "theme", "blue");
		switch (theme)
		{
			case "blue":
				rgb = getResources().getColor(R.color.default_colorPrimary);
				setTheme(R.style.AppTheme);
				break;
			case "yellow":
				rgb = getResources().getColor(R.color.yellow_colorPrimary);
				setTheme(R.style.Yellow);
				break;
			case "pink":
				rgb = getResources().getColor(R.color.pink_colorPrimary);
				setTheme(R.style.Pink);
				break;
			case "green":
				rgb = getResources().getColor(R.color.green_colorPrimary);
				setTheme(R.style.Green);
				break;
			case "addedblue":
				rgb = getResources().getColor(R.color.addedblue_colorPrimary);
				setTheme(R.style.AddBlue);
				break;
			case "umr":
				rgb = getResources().getColor(R.color.umr_colorPrimary);
				setTheme(R.style.UMR);
				break;
			case "night":
				rgb = getResources().getColor(R.color.nightmode_colorPrimary);
				setTheme(R.style.NightMode);
				break;
			case "cus":
				rgb = Color.parseColor((String)(u.getPreference(Utils.PreferenceType.STRING, "custom_rgb", "#123456")));
				break;
		}
	}
}
