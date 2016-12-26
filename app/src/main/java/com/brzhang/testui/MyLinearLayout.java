package com.brzhang.testui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by brzhang on 16/12/25.
 * Description :
 */

public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent() called with: " /*+ "event = [" + event + "]"*/+ "on  id = [" + getId() + "]");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.e(TAG, "onInterceptTouchEvent() called with: " /*+ "ev = [" + ev + "]"*/+ "on  id = [" + getId() + "]");
        return super.onInterceptTouchEvent(ev);
        //return true;
    }
}
