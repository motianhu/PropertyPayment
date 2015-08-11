package com.smona.app.propertypayment.power;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.PaymentBaseActivity;

public class PowerActivity extends PaymentBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_power);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_power);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {

    }

    @Override
    protected void clickView(View v) {

    }

}
