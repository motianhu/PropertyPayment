package com.smona.app.propertypayment.heat;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentBaseDataAdapter;
import com.smona.app.propertypayment.heat.bean.PaymentHeatDetailItemBean;

public class PaymentHeatDetailAdapter extends PaymentBaseDataAdapter {

    public PaymentHeatDetailAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.payment_heat_detail_list_item, null);
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {

        if (!(info instanceof PaymentHeatDetailItemBean)) {
            return;
        }

        final PaymentHeatDetailItemBean item = (PaymentHeatDetailItemBean) info;

        TextView qianfei = (TextView) convertView.findViewById(R.id.qianfei);
        qianfei.setText(item.qianfei);

        TextView danjia = (TextView) convertView.findViewById(R.id.danjia);
        danjia.setText(item.danjia);

        TextView zhinajin = (TextView) convertView.findViewById(R.id.zhinajin);
        zhinajin.setText(item.zhinajin);

        TextView yingjiao = (TextView) convertView.findViewById(R.id.yingjiao);
        yingjiao.setText(item.yingjiao);
    }
}
