package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.util.LogUtil;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class PaymentBaseDataAdapter extends BaseAdapter {
    protected Context mContext;
    protected ArrayList<PaymentItemInfo> mContent;

    public PaymentBaseDataAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        mContext = context;
        mContent = content;
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public Object getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaymentItemInfo info = mContent.get(position);
        LogUtil.d("motianhu", "convertView: " + convertView + ", position: " + position);
        if (convertView == null) {
            convertView = createContentView(mContext);
            convertView.setTag(info);
        }
        initConvertView(convertView, info);
        return convertView;
    }

    public abstract View createContentView(Context context);

    public abstract void initConvertView(View view, PaymentItemInfo info);

    protected void gotoDetail(PaymentItemInfo info) {
        Intent intent = createIntent();
        intent.putExtra("iteminfo", info);
        mContext.startActivity(intent);
    }

    protected Intent createIntent() {
        return null;
    }
}
