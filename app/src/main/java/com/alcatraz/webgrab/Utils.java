package com.alcatraz.webgrab;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.view.ViewGroup.*;
import android.widget.*;
import com.alcatraz.webgrab.bean.*;
import java.util.*;
import android.app.*;
import android.util.*;
import com.alcatraz.support.v4.appcompat.*;
import android.support.design.widget.*;
import android.database.sqlite.*;
import android.webkit.*;
import com.alcatraz.webgrab.activities.*;
import android.net.*;

public class Utils
{
	SharedPreferences spf;
	SharedPreferences.Editor edit;
	Context ctx;
	int color_const=Color.parseColor("#3f51b5");
	int color_textCONST=Color.parseColor("#dddddd");
	public Utils(Context c){
		ctx=c;
		spf=c.getSharedPreferences(c.getPackageName()+"_preferences",c.MODE_PRIVATE);
		edit=spf.edit();
	}
	public void savePreference(PreferenceType t,String key,Object content){
		if(t==PreferenceType.STRING){
			edit.putString(key,(String)content);
		}else if(t==PreferenceType.INTEGER){
			edit.putInt(key,(Integer)content);
		}else if(t==PreferenceType.BOOLEAN){
			edit.putBoolean(key,(Boolean)content);
		}else if(t==PreferenceType.FLOAT){
			edit.putFloat(key,(Float)content);
		}else if(t==PreferenceType.LONG){
			edit.putLong(key,(Long)content);
		}else if(t==PreferenceType.STRINGSET){
			edit.putStringSet(key,(Set<String>)content);
		}
		edit.commit();
	}
	public Object getPreference(PreferenceType t,String key,Object defv){
		Object parameter = null;
		if(t==PreferenceType.STRING){
			parameter=spf.getString(key,(String)defv);
		}else if(t==PreferenceType.INTEGER){
			parameter=spf.getInt(key,(Integer)defv);
		}else if(t==PreferenceType.BOOLEAN){
			parameter=spf.getBoolean(key,(Boolean)defv);
		}else if(t==PreferenceType.FLOAT){
			parameter=spf.getFloat(key,(Float)defv);
		}else if(t==PreferenceType.LONG){
			parameter=spf.getLong(key,(Long)defv);
		}else if(t==PreferenceType.STRINGSET){
			parameter=spf.getStringSet(key,(Set<String>)defv);
		}
		
		return parameter;
	}
	
	public String convertToARGB(int color) {
        // alpha = Integer.toHexString(Color.alpha(color));
		String red = Color.red(color)+"";
        String green = Color.green(color)+"";
        String blue = Color.blue(color)+"";
        // System.out.println(Color.red(color))
        return "#" + red + green + blue;
    }
	public int Dp2Px(Context context, float dp) { 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int) (dp * scale + 0.5f); 
	} 
	public enum PreferenceType{
		STRING,STRINGSET,INTEGER,LONG,BOOLEAN,FLOAT
	}
	public void showCookieDialog(final Activity act,final String url,final WebView vw,boolean requestsnack)
	{
		final View root=act.getLayoutInflater().inflate(R.layout.cookie_panel,null);
		String host1=Analyser.getHost(url);
		TableLayout rl=(TableLayout) root.findViewById(R.id.cookiepanelTableLayout1);
		TextView Host=(TextView) root.findViewById(R.id.cookiepanelTextView1);
		Host.setText(host1);
		List<CookieSQLItem> all=Colunms.getCookieTable(act);
		List<CookieSQLItem> processed=new LinkedList<CookieSQLItem>();
		CookieSQLItem sql=new CookieSQLItem();
		sql.setCreationUTC(act.getString(R.string.cookie_create));
		sql.setHostKey(act.getString(R.string.cookie_host_key));
		sql.setName(act.getString(R.string.cookie_name));
		sql.setValue(act.getString(R.string.cookie_value));
		sql.setPath(act.getString(R.string.cookie_path));
		sql.setExpiresUTC(act.getString(R.string.cookie_expiresutc));
		sql.setSecure(act.getString(R.string.cookie_secure));
		sql.setLastAccessUTC(act.getString(R.string.cookie_lastaccessutc));
		sql.setHttpOnly(act.getString(R.string.cookie_httponly));
		sql.setHasExpired(act.getString(R.string.cookie_has_expires));
		sql.setPersistent(act.getString(R.string.cookie_persistent));
		sql.setPriority(act.getString(R.string.cookie_priority));
		sql.setRawEncry(act.getString(R.string.cookie_encry).getBytes());
		sql.setEncryptedValue(act.getString(R.string.cookie_encry));
		sql.setFirstPartyOnly(act.getString(R.string.cookie_firsr_party_only));
		processed.add(sql);
		final android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(act)
			.setView(root)
			.show();
		for(CookieSQLItem b:all){
			//Log.e("Alc_f12",b.getString());
			String cutsql[]=b.getHostKey().split("\\.");
			String cuturl[]=host1.split("\\.");
			int itera=0;
			for(String ci:cutsql){
				for(String uc:cuturl){
					
					if(uc.equals(ci)){
						if((!(ci.equals("com"))&&(!(ci.equals("m"))))){
						itera++;
						}
					}
				}
			}
			if(itera>=1){
				processed.add(b);
			}
		}
		for(final CookieSQLItem cookieSQLItem:processed){
			List<View> views=new LinkedList<View>();
			List<String> data=cookieSQLItem.getArrayObject();
			int iter=0;
			for(String i:data){
				TextView txv=new TextView(act);
				txv.setText(i);
				if(cookieSQLItem.getEncryptedValue().equals(act.getString(R.string.cookie_encry))){
					txv.setBackgroundColor(color_const);
					txv.setTextColor(color_textCONST);
				}
				if(iter==0){
					txv.setPadding(Dp2Px(act,14),0,Dp2Px(act,14),0);
				}else{
					txv.setPadding(0,0,Dp2Px(act,14),0);
				}
				iter++;
				views.add(txv);
			}
			if(requestsnack){
				Snackbar.make(root,R.string.cookie_toast,Snackbar.LENGTH_LONG)
					.setAction(R.string.cookie_restart, new OnClickListener(){

						@Override
						public void onClick(View p1)
						{
							/*android.os.Process.killProcess(android.os.Process.myPid());
							act.startActivity(new Intent(act,MainActivity.class).setData(Uri.parse(url)));*/
							act.sendBroadcast(new Intent().setAction(MainActivity.THEME_ACTION));
							// TODO: Implement this method
						}
					})
					
					.show();
			}
			TableRow tr=new TableRow(act);
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			for(View txv:views){
				tr.addView(txv);
			}
			tr.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						showCookieEdit(act, cookieSQLItem, new dialogcnn(){

								@Override
								public void onPositive()
								{
									a.dismiss();
									
									showCookieDialog(act,url,vw,true);
									
									// TODO: Implement this method
								}
							},url);
						// TODO: Implement this method
					}
				});
			rl.addView(tr);
		}
		
		// TODO: Implement this method
	}
	
	interface dialogcnn{
		public void onPositive();
	}
	private void showCookieEdit(final Activity act,final CookieSQLItem sql,final dialogcnn x,final String url){
		View v=act.getLayoutInflater().inflate(R.layout.cookiesqledit, null);
		final TextInputLayout til=(TextInputLayout) v.findViewById(R.id.textInputLayout12);
		til.getEditText().setText(sql.getValue());
		final android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(act)
			.setTitle(sql.getName())
			.setView(v)
			.setNegativeButton(R.string.ad_nb1, null)
			.setPositiveButton(R.string.ad_pb1, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					SQLiteDatabase sqldb=act.openOrCreateDatabase("/data/data/"+ctx.getPackageName()+"/app_webview/Cookies", ctx.MODE_PRIVATE, null);
					ContentValues cv=new ContentValues();
					cv.put("value",til.getEditText().getText().toString().trim());
					sqldb.update("cookies",cv,"creation_utc=?",new String[]{sql.getCreationUTC()});
					
					x.onPositive();
					// TODO: Implement this method
				}
			})
			.create();

		new AlertDialogUtil().setSupportDialogColor(a,color_const);
		a.show();
	}
}
