package com.alcatraz.webgrab.bean;
import java.io.*;
import android.util.*;
import java.util.*;

public class CookieSQLItem
{
	private String creation_utc;
	private String host_key;
	private String name;
	private String value;
	private String path;
	private String expires_utc;
	private String secure;
	private String httponly;
	private String last_access_utc;
	private String has_expires;
	private String persistent;
	private String priority;
	private String encrypted_value;
	private byte[] encry_raw;
	private String firstpartyonly;
	private List<String> tags;
	public CookieSQLItem(){
		tags=new LinkedList<String>();
		tags.add("creation_utc");
	}
	public String getCreationUTC(){
		return creation_utc;
	}
	public void setCreationUTC(String data){
		creation_utc=data;
	}
	public byte[] getRawEncry(){
		return encry_raw;
	}
	public void setRawEncry(byte[] data){
		encry_raw=data;
	}
	public String getHostKey(){
		return host_key;
	}
	public void setHostKey(String data){
		host_key=data;
	}
	public String getName(){
		return name;
	}
	public void setName(String data){
		name=data;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String data){
		value=data;
	}
	public String getPath(){
		return path;
	}
	public void setPath(String data){
		path=data;
	}
	public String getExpiresUTC(){
		return expires_utc;
	}
	public void setExpiresUTC(String data){
		expires_utc=data;
	}
	public String getSecure(){
		return secure;
	}
	public void setSecure(String data){
		secure=data;
	}
	public String getHttpOnly(){
		return httponly;
	}
	public void setHttpOnly(String data){
		httponly=data;
	}
	public String getLastAccessUTC(){
		return last_access_utc;
	}
	public void setLastAccessUTC(String data){
		last_access_utc=data;
	}
	public String getHasExpired(){
		return has_expires;
	}
	public void setHasExpired(String data){
		has_expires=data;
	}
	public String getPersistent(){
		return persistent;
	}
	public void setPersistent(String data){
		persistent=data;
	}
	public String getPriority(){
		return priority;
	}
	public void setPriority(String data){
		priority=data;
	}
	public String getEncryptedValue(){
		return encrypted_value;
	}
	public void setEncryptedValue(String data){
		encrypted_value=data;
	}
	public String getFirstPartyOnly(){
		return firstpartyonly;
	}
	public void setFirstPartyOnly(String data){
		firstpartyonly=data;
	}
	public String getString(){
		String s= creation_utc+","+host_key+","+name+","+value+","+path+","+expires_utc+","+secure+","+httponly+","+last_access_utc+","+has_expires+","+persistent+","+priority+","+encrypted_value+","+firstpartyonly;
		return s;
	}
	public void printLog(String tag){
		Log.e(tag,getString());
	}
	public LinkedList<String> getArrayObject(){
		LinkedList<String> readyto=new LinkedList<String>();
		readyto.add(creation_utc);
		readyto.add(host_key);
		readyto.add(name);
		readyto.add(value);
		readyto.add(path);
		readyto.add(expires_utc);
		readyto.add(secure);
		readyto.add(httponly);
		readyto.add(last_access_utc);
		readyto.add(has_expires);
		readyto.add(persistent);
		readyto.add(priority);
		readyto.add(encrypted_value);
		readyto.add(firstpartyonly);
		return readyto;
	}
}
