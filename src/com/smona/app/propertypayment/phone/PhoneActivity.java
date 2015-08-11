package com.smona.app.propertypayment.phone;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.PaymentBaseActivity;

public class PhoneActivity extends PaymentBaseActivity {
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
    }

    @Override
    protected void initBody() {

    }

    @Override
    protected void clickView(View v) {

    }

}
