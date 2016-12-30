package com.brzhang.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

import com.brzhang.testui.R;


/**
 * Created by brzhang on 16/12/21.
 * 信纸一样的编辑框
 */

public class PlainEditText extends EditText {


    private static final String TAG = "PlainEditText";
    private Bitmap mLineBitmap;

    private int needShowLineNum = 8;

    private int mLine = R.drawable.ic_plain_editer_line;
    private Rect mLineRect = new Rect();


    public PlainEditText(Context context) {
        this(context, null);
    }

    public PlainEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlainEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mLineBitmap = BitmapFactory.decodeResource(getResources(), mLine);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int lineHeight = this.getLineHeight();
        int topPadding = this.getPaddingTop();
        int leftPadding = this.getPaddingLeft();
        int rightPadding = this.getPaddingRight();
        float textSize = getTextSize();
        setGravity(Gravity.LEFT | Gravity.START | Gravity.TOP);
        int y = (int) (topPadding + textSize);
        int maxLine = getMaxLines() > 10000 ? 0 : getMaxLines();

        for (int i = 0; i < maxLine | i < getLineCount() | i < needShowLineNum; i++) {
            mLineRect.left = leftPadding;
            mLineRect.right = getMeasuredWidth() - rightPadding;
            mLineRect.top = (int) (y + getLineSpacingExtra() * 1 / 2);
            mLineRect.bottom = (int) (y + getLineSpacingExtra() * 1 / 2 + mLineBitmap.getHeight());
            canvas.drawBitmap(mLineBitmap, null, mLineRect, null);
            y += lineHeight;
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        float add = getLineSpacingExtra();
        float mul = getLineSpacingMultiplier();
        setLineSpacing(0f, 1f);
        setLineSpacing(add, mul);
    }

}