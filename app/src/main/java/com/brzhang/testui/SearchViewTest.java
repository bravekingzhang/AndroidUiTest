package com.brzhang.testui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ViewFlipper;

import static android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY;
import static android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS;
import static android.view.inputmethod.InputMethodManager.SHOW_FORCED;

public class SearchViewTest extends AppCompatActivity {


    private enum SEARCH_STATE{
        STATE_INIT,
        STATE_SEARCH
    }

    private SEARCH_STATE mState = SEARCH_STATE.STATE_INIT;

    ViewFlipper mViewFlipper;
    EditText mEtquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEtquery = (EditText) findViewById(R.id.edit_query);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mViewFlipper.setDisplayedChild(0);
        mState = SEARCH_STATE.STATE_INIT;


        mViewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState == SEARCH_STATE.STATE_INIT){
                    mState = SEARCH_STATE.STATE_SEARCH;
                    mViewFlipper.setDisplayedChild(1);
                    showKeyBoard();
                }else{
                    mState = SEARCH_STATE.STATE_INIT;
                    mViewFlipper.setDisplayedChild(0);
                    mEtquery.setText("");
                    hideKeyBoard();
                }
            }
        });
    }

    private void showKeyBoard() {
        mEtquery.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEtquery,0);
    }

    private void hideKeyBoard(){
        mEtquery.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(mEtquery.getWindowToken(),0);
    }


}
