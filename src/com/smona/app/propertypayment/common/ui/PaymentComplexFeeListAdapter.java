package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.heat.bean.PaymentHeatDetailBean;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerDetailBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDetailBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PaymentComplexFeeListAdapter extends PaymentBaseDataAdapter {

    public PaymentComplexFeeListAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.payment_complex_list_detail_item, null);
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {
        if (info instanceof PaymentPropertyDetailBean) {
            setupPropertyDetailViews(convertView,
                    ((PaymentPropertyDetailBean) info));
        } else if (info instanceof PaymentParkDetailBean) {
            setupParkDetailViews(convertView, ((PaymentParkDetailBean) info));
        } else if (info instanceof PaymentPowerDetailBean) {
            setupPowerDetailViews(convertView, ((PaymentPowerDetailBean) info));
        } else if (info instanceof PaymentHeatDetailBean) {
            setupHeatDetailViews(convertView, ((PaymentHeatDetailBean) info));
        } else {
            setupPhoneDetailViews(convertView, null);
        }
    }

    private void setupPropertyDetailViews(View convertView,
            PaymentPropertyDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_park_company);
        initText(parent, R.id.value, item.propertyname);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);
        initText(parent, R.id.value, item.payfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_common_pay_time);
        initText(parent, R.id.value, item.paydate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.name, R.string.payment_common_fangchan_info);
        initText(parent, R.id.value, item.housingbantransname);
    }

    private void setupParkDetailViews(View convertView,
            PaymentParkDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_park_company);
        initText(parent, R.id.value, item.propertyname);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);
        initText(parent, R.id.value, item.payfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_common_pay_time);
        initText(parent, R.id.value, item.paydate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.name, R.string.payment_common_cheliang_info);
        initText(parent, R.id.value, item.carnum);
    }

    private void setupPowerDetailViews(View convertView,
            PaymentPowerDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_common_huming);
        initText(parent, R.id.value, item.trans_name);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);
        initText(parent, R.id.value, item.payfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_power_account_date);
        initText(parent, R.id.value, item.accountdate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.name, R.string.payment_power_jiaofei_result);
        initText(parent, R.id.value, item.paydscrp);
    }
    
    private void setupHeatDetailViews(View convertView,
            PaymentHeatDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_heat_detail_username);
        initText(parent, R.id.value, item.trans_name);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_heat_detail_total_zhinajin);
        initText(parent, R.id.value, item.payfare);

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_heat_detail_total_qianfei);
        initText(parent, R.id.value, item.accountdate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.name, R.string.payment_heat_detail_totoal_year);
        initText(parent, R.id.value, item.paydscrp);
    }

    private void setupPhoneDetailViews(View convertView,
            PaymentPowerDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_common_yunyingshang);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.name, R.string.payment_common_phone_info);
    }

    private void initText(View parent, int childId, int textId) {
        TextView text = (TextView) parent.findViewById(childId);
        text.setText(textId);
    }

    private void initText(View parent, int childId, String value) {
        TextView text = (TextView) parent.findViewById(childId);
        text.setText(value);
    }

}
