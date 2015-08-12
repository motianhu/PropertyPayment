package com.smona.app.propertypayment.park;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;

public class ParkActivity extends PaymentBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_park);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_park);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {

    }

    @Override
    protected void clickView(View v) {

    }

}
