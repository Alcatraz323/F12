package com.alcatraz.webgrab.bean;
import java.util.*;
import org.jsoup.nodes.*;

public class AlternateElement
{
	
	private String id;
	private String tagname;
	private String value;
	private String name;
	private String type;
	private String classname;
	private String innerHTML;
	private Map<String,String> attributes;
	/*Offset*/
	private String offsetHeight;
	private String offsetWidth;
	private String offsetLeft;
	private String offsetParent;
	private String offsetTop;
	/*Scroll*/
	private String scrollHeight;
	private String scrollWidth;
	private String scrollTop;
	private String scrollLeft;
	private String title;
	private String style;
	private Map<Integer,AlternateElement> childs;
	private AlternateElement parent;
	public AlternateElement(){
		attributes=new HashMap<String,String>();
		childs=new HashMap<Integer,AlternateElement>();
	}
}
