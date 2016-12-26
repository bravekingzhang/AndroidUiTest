package com.brzhang.testui;

import android.text.Editable;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by brzhang on 16/12/25.
 * Description :
 */

public class MyTextKeyListner extends TextKeyListener {

    private static final String TAG = "MyTextKeyListner";
    /**
     * Creates a new TextKeyListener with the specified capitalization
     * and correction properties.
     *
     * @param cap      when, if ever, to automatically capitalize.
     * @param autotext whether to automatically do spelling corrections.
     */
    public MyTextKeyListner(Capitalize cap, boolean autotext) {
        super(cap, autotext);
    }
    @Override
    public int getInputType() {
        Log.e(TAG, "getInputType() called with: " + "");
        return super.getInputType();
    }

    @Override
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown() called with: " + "view = [" + view + "], content = [" + content + "], keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyDown(view, content, keyCode, event);
    }

    @Override
    public boolean onKeyUp(View view, Editable content, int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyUp() called with: " + "view = [" + view + "], content = [" + content + "], keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyUp(view, content, keyCode, event);
    }

    @Override
    public boolean onKeyOther(View view, Editable content, KeyEvent event) {
        Log.e(TAG, "onKeyOther() called with: " + "view = [" + view + "], content = [" + content + "], event = [" + event + "]");
        return super.onKeyOther(view, content, event);
    }

    @Override
    public long clearMetaKeyState(long state, int which) {
        Log.e(TAG, "clearMetaKeyState() called with: " + "state = [" + state + "], which = [" + which + "]");
        return super.clearMetaKeyState(state, which);
    }
}
