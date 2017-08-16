package com.alcatraz.webgrab;
import android.webkit.*;
import android.content.*;
import android.util.*;
import android.view.*;

public class WebView4Scroll extends WebView{

    public WebView4Scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
		
    }
	public WebView4Scroll(Context context){
		super(context);
	}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(this.getScrollY() <= 0)
					this.scrollTo(0,1);
                break;
            case MotionEvent.ACTION_UP:
//                if(this.getScrollY() == 0)
//                this.scrollTo(0,-1);
                break;
        }
        return super.onTouchEvent(event);
    }
}
