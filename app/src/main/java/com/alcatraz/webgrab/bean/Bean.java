package com.alcatraz.webgrab.bean;
import org.jsoup.nodes.*;
import org.apache.http.conn.util.*;
import android.os.*;

public class Bean
{
	private Node data;
	private int pxpadding;
	private boolean isI;
	public Bean(Node data,int px,boolean is){
		this.data=data;
		pxpadding=px;
		isI=is;
	}
	public Node getData(){
		return data;
	}
	public int getPadding(){
		return pxpadding;
	}
	public boolean isI(){
		return isI;
	}
	public void setIs(boolean is){
		isI=is;
	}
}
