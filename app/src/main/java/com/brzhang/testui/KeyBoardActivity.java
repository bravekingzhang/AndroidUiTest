package com.brzhang.testui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class KeyBoardActivity extends AppCompatActivity {


    LinearLayout mLinearLayout;
    ScrollView mScrollView;
    EditText mEditText;
    LinearLayout emojBar;

    private int initScrollViewHeight = 0;

    private static final String TAG = "KeyBoardActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLinearLayout = (LinearLayout) findViewById(R.id.root_view);

        mScrollView = (ScrollView) findViewById(R.id.scrollView);

        emojBar = (LinearLayout) findViewById(R.id.emoj_bar);


        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (initScrollViewHeight == 0){
                    initScrollViewHeight = mScrollView.getHeight();
                }else if (initScrollViewHeight - mScrollView.getHeight() >300){
                    Toast.makeText(KeyBoardActivity.this,"键盘打开了",Toast.LENGTH_SHORT).show();
                    emojBar.setVisibility(View.VISIBLE);
                }else if (initScrollViewHeight - mScrollView.getHeight()<200){
                    emojBar.setVisibility(View.GONE);
                    Toast.makeText(KeyBoardActivity.this,"键盘隐藏了",Toast.LENGTH_SHORT).show();
                }
                Log.e(TAG, "onGlobalLayout() called with: mScrollView height" + mScrollView.getHeight());
            }
        });

        mEditText = (EditText) findViewById(R.id.edit_text);
    }

}
