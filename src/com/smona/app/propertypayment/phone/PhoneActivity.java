package com.smona.app.propertypayment.phone;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeePayActivity;

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
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.input_phone);
        initView(R.id.input_phone);

        parent = mRoot.findViewById(R.id.phone_guishu);
        initText(parent, R.id.name, "河南电信");

        initText(R.id.next_step, R.string.payment_phone_liji_pay);
        initView(R.id.next_step);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_phone_select_jine);
        initView(R.id.dazhe_info);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.dazhe_info:
            startActivityForResult(new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI), 0);
            break;
        case R.id.detail:
            gotoSubActivity(PaymentSimpleFeeDetailListActivity.class);
            break;
        case R.id.input_phone:
            gotoSubActivity(PhoneHistoryListActivity.class);
            break;
        case R.id.next_step:
            gotoSubActivity(PaymentSimpleFeePayActivity.class);
            break;
        }
    }

}
