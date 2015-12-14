package com.smona.app.propertypayment.common.simple;

import com.smona.app.propertypayment.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentSimpleSelectFeeView extends LinearLayout {

    private int mTextViewWidth = 0;
    private int mTextViewGap = 0;

    public PaymentSimpleSelectFeeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextViewWidth = context.getResources().getDimensionPixelSize(
                R.dimen.payment_power_fee_text_view_width);
        mTextViewGap = context.getResources().getDimensionPixelSize(
                R.dimen.payment_power_fee_text_view_gap);
    }

    @SuppressLint("InflateParams")
    public void addTextView(int value, OnClickListener listener) {
        Context context = getContext();
        TextView text = (TextView) LayoutInflater.from(context).inflate(
                R.layout.payment_power_select_fee_item, null);
        text.setText(value
                + getResources().getString(R.string.payment_common_rmb));
        text.setTag(value);
        LayoutParams params = new LayoutParams(mTextViewWidth,
                LayoutParams.MATCH_PARENT);
        text.setOnClickListener(listener);
        params.leftMargin = mTextViewGap;
        params.rightMargin = mTextViewGap;
        addView(text, params);
    }

    public void setSelected(View v) {
        int count = getChildCount();
        View child = null;
        for (int index = 0; index < count; index++) {
            child = getChildAt(index);
            child.setSelected(child == v);
        }
    }
}
