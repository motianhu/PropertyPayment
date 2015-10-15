package com.smona.app.propertypayment.park;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountBean;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.park.bean.PaymentParkCheweiBean;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class PaymentParkTypeAdapter extends PaymentTypeAdapter {

    public PaymentParkTypeAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    public void initConvertView(View convertView, final PaymentItemInfo info) {
        TextView text = (TextView) convertView;
        String name = "";
        if(info instanceof PaymentParkCheweiBean) {
            PaymentParkCheweiBean chewei = ((PaymentParkCheweiBean) info);
            name = chewei.getCarInfo();    
        } else if(info instanceof PaymentDiscountBean) {
            PaymentDiscountBean discount = ((PaymentDiscountBean) info);
            name = discount.getDiscountName(mContext);
        }
        text.setText(name);
        text.setTextColor(Color.BLACK);
    }
}
