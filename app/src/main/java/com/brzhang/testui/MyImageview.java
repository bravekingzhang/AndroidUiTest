package com.brzhang.testui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by brzhang on 16/12/25.
 * Description :
 */

public class MyImageview extends ImageView {

    private static final String TAG = "MyImageview";

    public MyImageview(Context context) {
        super(context);
    }

    public MyImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent() called with: " + "event = [" + event + "]");
        //return super.onTouchEvent(event);
        return true;
    }

}
