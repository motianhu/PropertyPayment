package com.smona.app.propertypayment.power;

import java.lang.reflect.Type;

import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.PaymentTypeItem;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeActivity;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.power.process.PaymentPowerMessageProcessProxy;

public class PowerActivity extends PaymentSimpleFeeActivity {
    private static final String TAG = "PowerActivity";

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
    protected void initCompanys() {
        for (int i = 0; i < 5; i++) {
            PaymentTypeItem type = new PaymentTypeItem();
            type.type_id = i + "";
            type.type_name = "Company " + i;
            mPayCompanys.add(type);
        }
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();

        mMessageProcess = new PaymentPowerMessageProcessProxy();
        ((PaymentPowerMessageProcessProxy) mMessageProcess).requestUserInfo(
                this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "content: " + content);
        if (PaymentPowerMessageProcessProxy.MSG_POWER_USER_INFO_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                requestRefreshUI();
            } else {

            }
        } else if (PaymentPowerMessageProcessProxy.MSG_POWER_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                requestRefreshUI();
            } else {

            }
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {
        
    }

}
