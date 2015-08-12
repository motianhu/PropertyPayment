package com.smona.app.propertypayment.common.ui;

import android.os.Bundle;

import com.smona.app.propertypayment.R;

public abstract class PaymentSimpleFeeActivity extends PaymentBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee);
        initViews();
    }

}
