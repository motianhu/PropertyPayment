package com.smona.app.propertypayment.gas;

import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentTypeItem;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeActivity;

public class GasActivity extends PaymentSimpleFeeActivity {

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_gas);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_gas_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_gas_company);
        initView(R.id.select_company);

        parent = mRoot.findViewById(R.id.input_huhao);
        initTextHint(parent, R.id.value, R.string.payment_gas_input_huhao);
        initText(parent, R.id.name, R.string.payment_gas_huhao);
        initView(R.id.input_huhao);

        parent = mRoot.findViewById(R.id.select_groupby);
        initTextHint(parent, R.id.select_type,
                R.string.payment_gas_select_groupby);
        initText(parent, R.id.select_type_value, R.string.payment_gas_groupby);
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
}
