package com.smona.app.propertypayment.common.simple;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.gas.bean.PaymentGasSubmitBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatFeeInfoBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatSubmitBean;
import com.smona.app.propertypayment.water.bean.PaymentWaterSubmitBean;

public class PaymentSimpleFeeActivity extends PaymentBaseActivity {

    private static final String TAG = PaymentSimpleFeeActivity.class
            .getSimpleName();

    protected PaymentSimpleSubmitBean mFeeDan;
    protected int mSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee_activity);
        acquireItemInfo();
        initViews();
    }

    private void acquireItemInfo() {
        mFeeDan = (PaymentSimpleSubmitBean) getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
        mSource = getIntent().getIntExtra(PaymentConstants.DATA_SOURCE, -1);
        LogUtil.d(TAG, "acquireItemInfo mSource: " + mSource + ", mFeeDan: "
                + mFeeDan);
    }

    @Override
    protected void initHeader() {
        initTitle();
        initView(R.id.back);
    }

    private void initTitle() {
        int resId = R.string.payment_home_power;
        if (PaymentConstants.DATA_SOURCE_WATER == mSource) {
            resId = R.string.payment_home_water;
        } else if (PaymentConstants.DATA_SOURCE_GAS == mSource) {
            resId = R.string.payment_home_gas;
        } else if (PaymentConstants.DATA_SOURCE_NONTAX == mSource) {
            resId = R.string.payment_home_nontax;
        } else if (PaymentConstants.DATA_SOURCE_HEAT == mSource) {
            resId = R.string.payment_home_heat;
        }
        initText(R.id.title, resId);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.username);
        initText(parent, R.id.name, R.string.payment_common_huming);
        initText(parent, R.id.value, mFeeDan.trans_name);

        parent = mRoot.findViewById(R.id.housecode);
        initText(parent, R.id.name, R.string.payment_common_huhao);
        String consno = "";
        if(mFeeDan instanceof PaymentHeatSubmitBean) {
            consno = ((PaymentHeatSubmitBean)mFeeDan).heatsno;
        } else if(mFeeDan instanceof PaymentGasSubmitBean) {
            consno = ((PaymentGasSubmitBean)mFeeDan).gasno;
        } else if(mFeeDan instanceof PaymentWaterSubmitBean) {
            consno = ((PaymentWaterSubmitBean) mFeeDan).consno;
        }
        initText(parent, R.id.value, consno);

        parent = mRoot.findViewById(R.id.needchange);
        initText(parent, R.id.name, R.string.payment_power_qianfei);
        initText(parent, R.id.value, mFeeDan.exchg_atm
                + getResources().getString(R.string.payment_common_rmb));

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.next_step:
            clickNextStep();
            break;
        }
    }

    private void clickNextStep() {
        gotoSubActivity(mFeeDan, mSource, PaymentSimpleConfirmActivity.class);
    }
}
