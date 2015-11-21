package com.smona.app.propertypayment.nontax;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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

        TextView kemu = (TextView) convertView.findViewById(R.id.kemu);
        kemu.setText(item.kemu);

        TextView leibie = (TextView) convertView.findViewById(R.id.leibie);
        leibie.setText(item.leibie);

        TextView jine = (TextView) convertView.findViewById(R.id.jine);
        jine.setText(item.kemu);

        TextView bank = (TextView) convertView.findViewById(R.id.bank);
        bank.setText(item.bank);

        CheckBox box = (CheckBox) convertView.findViewById(R.id.status);
        box.setChecked(item.isSelected);
        box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                item.isSelected = isChecked;
            }

        });
    }
}
