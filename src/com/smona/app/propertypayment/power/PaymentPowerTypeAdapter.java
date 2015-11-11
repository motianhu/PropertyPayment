package com.smona.app.propertypayment.power;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.power.bean.PaymentPowerCityBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerCompanyBean;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class PaymentPowerTypeAdapter extends PaymentTypeAdapter {

    public PaymentPowerTypeAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    public void initConvertView(View convertView, final PaymentItemInfo info) {
        TextView text = (TextView) convertView;
        String name = "";
        if (info instanceof PaymentPowerCityBean) {
            PaymentPowerCityBean city = ((PaymentPowerCityBean) info);
            name = city.cityname;
        } else if (info instanceof PaymentPowerCompanyBean) {
            PaymentPowerCompanyBean discount = ((PaymentPowerCompanyBean) info);
            name = discount.org_name;
        }
        text.setText(name);
        text.setTextColor(Color.BLACK);
    }
}
