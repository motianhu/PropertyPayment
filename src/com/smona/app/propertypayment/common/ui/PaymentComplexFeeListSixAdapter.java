package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.nontax.bean.PaymentNonTaxDetailItemBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PaymentComplexFeeListSixAdapter extends PaymentBaseDataAdapter {

    public PaymentComplexFeeListSixAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.payment_complex_list_detail_item_six, null);
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {
        if (info instanceof PaymentNonTaxDetailItemBean) {
            setupPropertyDetailViews(convertView,
                    ((PaymentNonTaxDetailItemBean) info));
        }
    }

    private void setupPropertyDetailViews(View convertView,
            PaymentNonTaxDetailItemBean item) {
        View parent = convertView.findViewById(R.id.kemu);
        initText(parent, R.id.name, R.string.payment_nontax_kemu_detail);
        initText(parent, R.id.value, item.stpaymentname);

        parent = convertView.findViewById(R.id.leibie);
        initText(parent, R.id.name, R.string.payment_nontax_leibie_detail);
        initText(parent, R.id.value, item.leibie);

        parent = convertView.findViewById(R.id.bank);
        initText(parent, R.id.name, R.string.payment_nontax_bank);
        initText(parent, R.id.value, item.bank);

        parent = convertView.findViewById(R.id.jine);
        initText(parent, R.id.name, R.string.payment_nontax_jine_detail);
        initText(parent, R.id.value, item.payfare
                + mContext.getResources()
                        .getString(R.string.payment_common_rmb));

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_common_pay_time);
        initText(parent, R.id.value, item.paydate);

        parent = convertView.findViewById(R.id.status);
        initText(parent, R.id.name, R.string.payment_nontax_status_detail);
        initText(parent, R.id.value, item.status);
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
