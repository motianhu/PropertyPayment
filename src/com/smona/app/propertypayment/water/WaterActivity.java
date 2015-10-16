package com.smona.app.propertypayment.water;

import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentTypeItem;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeActivity;
import com.smona.app.propertypayment.common.util.PaymentConstants;

public class WaterActivity extends PaymentSimpleFeeActivity {

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_water);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
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

        initText(R.id.next_step, R.string.payment_common_next_step);
        initView(R.id.next_step);
    }

    @Override
    protected void initCompanys() {
        for (int i = 0; i < 5; i++) {
            PaymentTypeItem type = new PaymentTypeItem();
            type.type_id = i + "";
            type.type_name = "Company " + i;
            mPayCompanys.add(type);
        }

    }

    protected void requestData() {
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {

    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {
    }

    @Override
    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_WATER;
    }
}
