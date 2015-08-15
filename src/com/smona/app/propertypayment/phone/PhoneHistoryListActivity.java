package com.smona.app.propertypayment.phone;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;

public class PhoneHistoryListActivity extends PaymentBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_phone);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_phone);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {

    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        }
    }

}
