package com.smona.app.propertypayment.property;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountBean;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.property.bean.PaymentPropertyFangchanBean;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class PaymentPropertyTypeAdapter extends PaymentTypeAdapter {

    public PaymentPropertyTypeAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    public void initConvertView(View convertView, final PaymentItemInfo info) {
        TextView text = (TextView) convertView;
        String name = "";
        if(info instanceof PaymentPropertyFangchanBean) {
            PaymentPropertyFangchanBean fangchan = ((PaymentPropertyFangchanBean) info);
            name = fangchan.getFangchanAddr();    
        } else if(info instanceof PaymentDiscountBean) {
            PaymentDiscountBean discount = ((PaymentDiscountBean) info);
            name = discount.getDiscountName(mContext);
        }
        text.setText(name);
        text.setTextColor(Color.BLACK);
    }
}
