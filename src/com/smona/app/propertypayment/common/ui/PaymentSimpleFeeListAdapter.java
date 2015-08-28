package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

import android.content.Context;
import android.view.View;

public class PaymentSimpleFeeListAdapter extends PaymentBaseDataAdapter {

    public PaymentSimpleFeeListAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @Override
    public View createContentView(Context context) {
        return null;
    }

    @Override
    public void initConvertView(View view, PaymentItemInfo info) {
        
    }
   
}
