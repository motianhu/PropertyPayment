package com.smona.app.propertypayment.water;

import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeDetailActivity;

public class WaterActivity extends PaymentSimpleFeeActivity {

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_water);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_water_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_water_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_water_company);
        initView(R.id.select_company);

        parent = mRoot.findViewById(R.id.input_huhao);
        initTextHint(parent, R.id.value, R.string.payment_water_input_huhao);
        initText(parent, R.id.name, R.string.payment_water_huhao);
        initView(R.id.input_huhao);

        parent = mRoot.findViewById(R.id.select_groupby);
        initTextHint(parent, R.id.select_type,
                R.string.payment_water_select_groupby);
        initText(parent, R.id.select_type_value, R.string.payment_water_groupby);
        initView(R.id.select_groupby);
        
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
            gotoSubActivity(PaymentSimpleFeeDetailActivity.class);
            break;
        case R.id.select_company:
            break;
        case R.id.select_groupby:
            break;
        case R.id.next_step:
            break;
        }
    }

}
