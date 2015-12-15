package com.smona.app.propertypayment.common.simple;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCityBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCompanyBean;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class PaymentSimpleTypeAdapter extends PaymentTypeAdapter {

    public PaymentSimpleTypeAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    public void initConvertView(View convertView, final PaymentItemInfo info) {
        TextView text = (TextView) convertView;
        String name = "";
        if (info instanceof PaymentSimpleCityBean) {
            PaymentSimpleCityBean city = ((PaymentSimpleCityBean) info);
            name = city.cityname;
        } else if (info instanceof PaymentSimpleCompanyBean) {
            PaymentSimpleCompanyBean discount = ((PaymentSimpleCompanyBean) info);
            name = discount.org_name;
        }
        text.setText(name);
        text.setTextColor(Color.BLACK);
    }
}
