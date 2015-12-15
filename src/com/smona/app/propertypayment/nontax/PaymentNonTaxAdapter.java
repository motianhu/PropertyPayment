package com.smona.app.propertypayment.nontax;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentBaseDataAdapter;
import com.smona.app.propertypayment.nontax.bean.PaymentNonTaxItemBean;

public class PaymentNonTaxAdapter extends PaymentBaseDataAdapter {

    public PaymentNonTaxAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.payment_nontax_list_item, null);
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {

        if (!(info instanceof PaymentNonTaxItemBean)) {
            return;
        }

        final PaymentNonTaxItemBean item = (PaymentNonTaxItemBean) info;

        TextView accode = (TextView) convertView.findViewById(R.id.kemu);
        accode.setText(item.accode);

        TextView paymentname = (TextView) convertView.findViewById(R.id.leibie);
        paymentname.setText(item.paymentname);

        TextView exchg_atm = (TextView) convertView.findViewById(R.id.jine);
        exchg_atm.setText(item.exchg_atm);

        TextView bank = (TextView) convertView.findViewById(R.id.bank);
        bank.setText(item.payment_addr);

        View box = convertView.findViewById(R.id.status);
        box.setSelected(item.isSelected);
    }
}
