package com.smona.app.propertypayment.power;

import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeePayActivity;

public class PowerActivity extends PaymentSimpleFeeActivity {

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_power);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_power_company);
        initView(R.id.select_company);

        parent = mRoot.findViewById(R.id.input_huhao);
        initTextHint(parent, R.id.value, R.string.payment_power_input_huhao);
        initText(parent, R.id.name, R.string.payment_power_huhao);
        initView(R.id.input_huhao);

        parent = mRoot.findViewById(R.id.select_groupby);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_groupby);
        initText(parent, R.id.select_type_value, R.string.payment_power_groupby);
        initView(R.id.select_groupby);

        initText(R.id.next_step, R.string.payment_common_next_step);
        initView(R.id.next_step);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case R.id.detail:
            gotoSubActivity(PaymentSimpleFeeDetailListActivity.class);
            break;
        case R.id.select_company:
            break;
        case R.id.select_groupby:
            break;
        case R.id.next_step:
            gotoSubActivity(PaymentSimpleFeePayActivity.class);
            break;
        }
    }

}
