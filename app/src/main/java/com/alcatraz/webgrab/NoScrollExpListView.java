package com.alcatraz.webgrab;
import android.widget.*;
import android.content.*;
import android.util.*;

public class NoScrollExpListView extends ExpandableListView
{
	public NoScrollExpListView(Context context) {
		super(context);
	}

	public NoScrollExpListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollExpListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
