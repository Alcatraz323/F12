package com.alcatraz.webgrab;
import java.io.*;
import android.os.*;

public class Output
{
	public static final String root=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data"+"/com.alcatraz.webgrab/";
	public static void write(File file,String content){
		if(!file.exists()){
			try
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			catch (Exception e)
			{}
		}
		try{
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content.getBytes());
		fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String read(File file){
		String line;
		String content=null;
		try{
		FileInputStream fis=new FileInputStream(file);
		if(fis!=null){
			InputStreamReader reader=new InputStreamReader(fis);
			BufferedReader buffreader = new BufferedReader(reader);
			while((line=buffreader.readLine())!=null){
				content=content+"\n";
				content+=line;
			}
			fis.close();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
}
