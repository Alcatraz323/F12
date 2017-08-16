package com.alcatraz.webgrab;
import java.net.*;
import android.content.*;
import android.content.res.*;

public class Analyser
{
	public static String getHost(String url){
		try
		{
			return new URL(url).getHost();
		}
		catch (MalformedURLException e)
		{
			return "(Unknow)";
		}
	}
	public static String getName(String url){
		String[] pro=url.split("/");
		return pro[pro.length-1];
	}
	public static int getResIDbyFileClass(String g){
		g=g.toLowerCase();
		if(g.contains(".js")||g.contains(".php")){
			return R.drawable.ic_file_document;
		}else if(g.contains(".png")||g.contains(".jpg")||g.contains(".jpeg")||g.contains(".ico")||g.contains(".ico")||g.contains(".bmp")||g.contains(".gif")||g.contains(".svg")){
			return R.drawable.ic_file_image;
		}else if(g.contains(".mp4")||g.contains(".flv")||g.contains(".3gp")||g.contains(".avi")||g.contains(".rm")||g.contains(".rmvb")||g.contains(".mkv")||g.contains(".wmv")){
			return R.drawable.ic_file_video;
		}else if(g.contains(".mp3")||g.contains(".wav")||g.contains(".wma")||g.contains(".ogg")||g.contains(".ape")||g.contains(".acc")){
			return R.drawable.ic_file_music;
		}else if(g.contains(".html")){
			return R.drawable.ic_file_xml;
		}
		return R.drawable.ic_file;
	}
	public static String getJavaScript(String mode){
		String plugin="";
		switch(mode){
			case "ERROR":
				plugin=".error(";
				break;
			case "LOG":
				plugin=".log(";
				break;
			case "WARNING":
				plugin=".warn(";
				break;
			case "TIP":
				plugin=".debug(";
				break;
				
		}
		String temp="javascript:console"+plugin;
		return temp;
	}
}
