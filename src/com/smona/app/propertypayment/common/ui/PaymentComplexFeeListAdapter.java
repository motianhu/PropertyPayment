package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDetailBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PaymentComplexFeeListAdapter extends PaymentBaseDataAdapter {

    private int mSource = -1;

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

    public void setSource(int source) {
        mSource = source;
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {
        View parent = convertView.findViewById(R.id.company_name);
        if (mSource == PaymentConstants.DATA_SOURCE_PHONE) {
            initText(parent, R.id.name, R.string.payment_common_yunyingshang);
        } else {
            initText(parent, R.id.name, R.string.payment_park_company);
        }

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_common_pay_time);

        parent = convertView.findViewById(R.id.objinfo);
        if (mSource == PaymentConstants.DATA_SOURCE_PARK) {
            initText(parent, R.id.name, R.string.payment_common_cheliang_info);
        } else if (mSource == PaymentConstants.DATA_SOURCE_PHONE) {
            initText(parent, R.id.name, R.string.payment_common_phone_info);
        } else if (mSource == PaymentConstants.DATA_SOURCE_PROPERTY) {
            initText(parent, R.id.name, R.string.payment_common_fangchan_info);
        }

        if (info instanceof PaymentPropertyDetailBean) {
            setupPropertyDetailViews(convertView,
                    ((PaymentPropertyDetailBean) info));
        } else if (info instanceof PaymentParkDetailBean) {
            setupParkDetailViews(convertView, ((PaymentParkDetailBean) info));
        } else {
            return;
        }
    }

    private void setupPropertyDetailViews(View convertView,
            PaymentPropertyDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.value, item.needfare);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.value, item.needfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.value, item.pstartdate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.value, item.paylinefare);
    }

    private void setupParkDetailViews(View convertView,
            PaymentParkDetailBean item) {
        View parent = convertView.findViewById(R.id.company_name);
        parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.value, item.needfare);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.value, item.needfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.value, item.pstartdate);

        parent = convertView.findViewById(R.id.objinfo);
        initText(parent, R.id.value, item.paylinefare);
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
