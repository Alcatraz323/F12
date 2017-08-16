package com.alcatraz.webgrab.widget;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import org.jsoup.nodes.*;

public class CodeView extends View
{
	Node dta;
	public CodeView(Context context) {
		super(context);
	}

	public CodeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CodeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		Paint p=new Paint();
		p.setAntiAlias(true);
		
	}
	public void setCodeText(Node raw){
		dta=raw;
		invalidate();
	}
}
