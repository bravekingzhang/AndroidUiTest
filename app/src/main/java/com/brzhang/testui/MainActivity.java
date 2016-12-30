package com.brzhang.testui;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ListView     mListView;
    private TextView     mTextView;
    private LinearLayout mView;
    private TextView     mScrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (LinearLayout) findViewById(R.id.contentPanel);
        mTextView = (TextView) findViewById(R.id.dialog_testalertTitle);
        //mTextView.setVisibility(View.GONE);
        mTextView.setText(R.string.alert_dialog_two_buttons2ultra_msg);
        mScrollerView = (TextView) findViewById(R.id.scrollView);

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View v = (View) layoutInflater.inflate(R.layout.list_view, null);
        mListView = (ListView) v.findViewById(R.id.select_dialog_listview);

        if (mListView != null) {
            final ViewGroup scrollParent = (ViewGroup) mScrollerView.getParent();
            final int childIndex = scrollParent.indexOfChild(mScrollerView);
            scrollParent.removeViewAt(childIndex);
            scrollParent.addView(mListView, childIndex);
        }
        makeOtherViews();

//		LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) mListView.getLayoutParams();
//		LayoutParams.
    }

    private void makeOtherViews() {
        ArrayList<SmsItem> smsItems = createSmsItems();

        SmsItemAdapter smsItemAdapter = new SmsItemAdapter(this, smsItems);
        mListView.setAdapter(smsItemAdapter);
        if (smsItemAdapter.getCount() >= 5) {
            mListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }
        smsItemAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent() called with: " + "event = [" + event + "]");
        return super.onTouchEvent(event);
    }

    private ArrayList<SmsItem> createSmsItems() {
        ArrayList<SmsItem> items = new ArrayList<SmsItem>();

        int len = array.length;

        for (int i = 0; i < len; i++) {
            SmsItem smsItem = new SmsItem();

            smsItem.setName(array[i]);
            smsItem.setText(array[i]);

            items.add(smsItem);
        }

        return items;
    }

    String[] array = new String[]{
            "10086",
            "蛇年到，",
            "周立",
            "蛇年快乐，大吉大利。",
            "Amada",
            "Happy new Year!",
            "Amada",
            "开心幸福数第一，看看信息变美丽，气死玫瑰无法比，青春脸",
            "Amada",
            " 点燃一只烛光，幸福万年长；点亮两只烛光，快乐无限亮",
            "13800138000",
            "本月话费余额为2元，请及时充值！",
            "李力",
            " 在这无比温馨的时刻，遥向你敬上一杯酒，连同我的衷心祝福，礼不贵",
            "13800138000",
            "本月话费余额为2元，请及时充值！",
            "李力",

            " 点燃一只烛光，幸福万年长；点亮两只烛光，快乐无限亮；当所有的烛光都点亮，美好的明天充满无限希望，吉祥的光芒永远萦绕你身旁。祝生日快乐！",
            "李佳 王明 王克", "周末一起活动，打球怎么样？", "10086",
            "1.发送 CXBX 到 10086,查询当月套餐剩余短信条数。", "95533",
            "过年想回家，支付网站使用建行网银支付！立马回家", "12580",
            "相思是一种浓浓的酒，总在举杯时散发出醉人的芬芳；乡愁是一份厚厚的情，总在月圆时轻唤起难言的惆怅", "周立",
            "蛇年到，群蛇向你送祝福：金环蛇祝你财源广进，银环蛇祝你聪明伶俐，赤链蛇祝你生意红火"};


}
