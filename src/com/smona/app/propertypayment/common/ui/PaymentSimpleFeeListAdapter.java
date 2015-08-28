package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.PaymentListItem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PaymentSimpleFeeListAdapter extends PaymentBaseDataAdapter {

    public PaymentSimpleFeeListAdapter(Context context,
            ArrayList<PaymentItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.payment_list_detail_item, null);
    }

    @Override
    public void initConvertView(View convertView, final PaymentItemInfo info) {
        View parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.name, R.string.payment_common_company);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.name, R.string.payment_common_pay_time);

        parent = convertView.findViewById(R.id.groupby);
        initText(parent, R.id.name, R.string.payment_common_groupby);

        parent = convertView.findViewById(R.id.housecode);
        initText(parent, R.id.name, R.string.payment_common_huhao);

        PaymentListItem item;
        if (info instanceof PaymentListItem) {
            item = (PaymentListItem) info;
        } else {
            return;
        }

        parent = convertView.findViewById(R.id.company_name);
        initText(parent, R.id.value, item.companyname);

        parent = convertView.findViewById(R.id.pay_money);
        initText(parent, R.id.value, item.money + "元");

        parent = convertView.findViewById(R.id.pay_time);
        initText(parent, R.id.value, item.paytime);

        parent = convertView.findViewById(R.id.groupby);
        initText(parent, R.id.value, item.groupname);

        parent = convertView.findViewById(R.id.housecode);
        initText(parent, R.id.value, item.housecode);
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
