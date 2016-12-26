
package com.brzhang.testui;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SmsItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<SmsItem> mSmsItems;

    public ArrayList<SmsItem> getmSmsItems() {
        return mSmsItems;
    }

    public void setmSmsItems(ArrayList<SmsItem> mSmsItems) {
        this.mSmsItems = mSmsItems;
    }

    public SmsItemAdapter(Context context, ArrayList<SmsItem> mSmsItems) {
        mContext = context;
        this.mSmsItems = mSmsItems;

    }

    @Override
    public int getCount() {
        if (mSmsItems != null) {

            return mSmsItems.size();

        }

        return 0;

    }

    @Override
    public Object getItem(int position) {
        if (mSmsItems != null) {

            return mSmsItems.get(position);

        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.sms_list, null);

        if (null != mSmsItems && mSmsItems.size() > position) {
            TextView name = (TextView) convertView.findViewById(R.id.sms_name);

            name.setText(mSmsItems.get(position).getName());

            TextView text = (TextView) convertView.findViewById(R.id.sms_text);

            text.setText(mSmsItems.get(position).getText());

        }

        return convertView;
    }

}
