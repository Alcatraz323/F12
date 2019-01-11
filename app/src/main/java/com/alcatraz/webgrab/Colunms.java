package com.alcatraz.webgrab;
import android.database.sqlite.*;
import android.database.*;
import android.util.*;
import android.content.*;
import java.util.*;
import com.alcatraz.webgrab.bean.*;

public class Colunms
{
	public static List<CookieSQLItem> getCookieTable(Context ctx){
		SQLiteDatabase sqLiteDatabase = ctx.openOrCreateDatabase("/data/data/"+ctx.getPackageName()+"/app_webview/Cookies", ctx.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("cookies", null, null, null, null, null, null);
		List<CookieSQLItem> csql=new LinkedList<CookieSQLItem>();
        while(cursor.moveToNext()){
                int columnIndex = cursor.getColumnIndex("creation_utc");
                String creationUtc = cursor.getString(columnIndex);
                columnIndex = cursor.getColumnIndex("host_key");
                String host_key = cursor.getString(columnIndex);
				String[] h=host_key.spl
				columnIndex = cursor.getColumnIndex("name");
                String name = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("value");
                String value = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("path");
                String path = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("expires_utc");
                String expires_utc= cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("secure");
                String secure = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("httponly");
                String httponly = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("last_access_utc");
                String last_access = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("has_expires");
                String has_expired = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("persistent");
                String persistent = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("priority");
                String priority = cursor.getString(columnIndex);
				columnIndex = cursor.getColumnIndex("encrypted_value");
                byte[] encry =cursor.getBlob(columnIndex);
				columnIndex = cursor.getColumnIndex("firstpartyonly");
                String firstparty = cursor.getString(columnIndex);
                CookieSQLItem sql=new CookieSQLItem();
				sql.setCreationUTC(creationUtc);
				sql.setHostKey(host_key);
				sql.setName(name);
				sql.setValue(value);
				sql.setPath(path);
				sql.setExpiresUTC(expires_utc);
				sql.setSecure(secure);
				sql.setLastAccessUTC(last_access);
				sql.setHttpOnly(httponly);
				sql.setHasExpired(has_expired);
				sql.setPersistent(persistent);
				sql.setPriority(priority);
				sql.setRawEncry(encry);
				sql.setEncryptedValue(ctx.getString(R.string.encry_def));
				sql.setFirstPartyOnly(firstparty);
				csql.add(sql);
				Log.e("Alc_f12",sql.toString());
            }
      //sqLiteDatabase.close();
		return csql;
	}
}
