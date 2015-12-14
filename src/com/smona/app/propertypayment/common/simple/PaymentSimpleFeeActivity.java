package com.smona.app.propertypayment.common.simple;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.submit.PaymentPowerSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;

public class PaymentSimpleFeeActivity extends PaymentBaseActivity {

    private static final String TAG = PaymentSimpleFeeActivity.class
            .getSimpleName();

    private static final int[] FEE_DATA = new int[] { 20, 50, 100, 200 };

    protected PaymentPowerSubmitBean mFeeDan;

    private PaymentSimpleSelectFeeView mSelectFee;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_power_fee_activity);
        acquireItemInfo();
        initViews();
    }

    private void acquireItemInfo() {
        mFeeDan = (PaymentPowerSubmitBean) getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
        LogUtil.d(TAG, "acquireItemInfo mItem: " + mFeeDan);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_power);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.username);
        initText(parent, R.id.name, R.string.payment_common_huming);
        initText(parent, R.id.value, mFeeDan.trans_name);

        parent = mRoot.findViewById(R.id.housecode);
        initText(parent, R.id.name, R.string.payment_common_huhao);
        initText(parent, R.id.value, mFeeDan.consno);

        parent = mRoot.findViewById(R.id.needchange);
        initText(parent, R.id.name, R.string.payment_power_qianfei);
        initText(parent, R.id.value, mFeeDan.exchg_atm
                + getResources().getString(R.string.payment_common_rmb));

        parent = mRoot.findViewById(R.id.input_fee);
        initTextHint(parent, R.id.value, R.string.payment_common_input_pay_jine);
        initText(parent, R.id.name, R.string.payment_common_pay_jine);

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);

        initSelectFee();
    }

    private void initSelectFee() {
        mSelectFee = (PaymentSimpleSelectFeeView) findViewById(R.id.select_fee);
        for (int value : FEE_DATA) {
            mSelectFee.addTextView(value, mSelectFeeListener);
        }
    }

    private OnClickListener mSelectFeeListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int value = (Integer) v.getTag();
            LogUtil.d(TAG, "moth " + value);
            mSelectFee.setSelected(v);
            View parent = mRoot.findViewById(R.id.input_fee);
            initText(parent, R.id.value, value + "");
        }

    };

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
        View parent = mRoot.findViewById(R.id.input_fee);
        String input_jine_str = ((TextView) parent.findViewById(R.id.value))
                .getText().toString();
        if (TextUtils.isEmpty(input_jine_str)) {
            showMessage("请输入缴费金额");
            return;
        }
        
        Double jine = Double.valueOf(input_jine_str);
        Double qianfei = Double.valueOf(mFeeDan.exchg_atm);
        if(qianfei > jine) {
            showMessage("缴费金额应大于等于欠费金额");
            return;
        }
        
        mFeeDan.transfare = input_jine_str;
        gotoSubActivity(mFeeDan, PaymentSimpleConfirmActivity.class);
    }
}
