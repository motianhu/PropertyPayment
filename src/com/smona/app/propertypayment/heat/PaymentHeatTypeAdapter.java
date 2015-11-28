package com.smona.app.propertypayment.heat;

import java.util.ArrayList;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCityBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCompanyBean;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class PaymentHeatTypeAdapter extends PaymentTypeAdapter {

    public PaymentHeatTypeAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    public void initConvertView(View convertView, final PaymentItemInfo info) {
        TextView text = (TextView) convertView;
        String name = "";
        if (info instanceof PaymentHeatCityBean) {
            PaymentHeatCityBean city = ((PaymentHeatCityBean) info);
            name = city.cityname;
        } else if (info instanceof PaymentHeatCompanyBean) {
            PaymentHeatCompanyBean discount = ((PaymentHeatCompanyBean) info);
            name = discount.org_name;
        }
        text.setText(name);
        text.setTextColor(Color.BLACK);
    }
}
