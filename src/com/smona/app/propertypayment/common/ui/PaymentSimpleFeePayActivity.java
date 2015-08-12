package com.smona.app.propertypayment.common.ui;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;

public class PaymentSimpleFeePayActivity extends PaymentBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee_pay_detail);
        acquireItemInfo();
        initViews();
    }

    private void acquireItemInfo() {

    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_detail_list);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.user_name);
        initText(parent, R.id.name, R.string.payment_common_huming);
        initText(parent, R.id.value, "ceshi");

        parent = mRoot.findViewById(R.id.user_huhao);
        initText(parent, R.id.name, R.string.payment_common_huhao);
        initText(parent, R.id.value, "ceshi");

        parent = mRoot.findViewById(R.id.pay_to_company);
        initText(parent, R.id.name, R.string.payment_common_company);
        initText(parent, R.id.value, "ceshi");

        parent = mRoot.findViewById(R.id.yue);
        initText(parent, R.id.name, R.string.payment_common_yue);
        initText(parent, R.id.value, "ceshi");

        parent = mRoot.findViewById(R.id.input_fee);
        initTextHint(parent, R.id.value, R.string.payment_common_input_pay_jine);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.next_step:
            break;
        }
    }
}
