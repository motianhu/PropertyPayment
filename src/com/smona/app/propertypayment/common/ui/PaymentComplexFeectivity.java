package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.util.LogUtil;

public abstract class PaymentComplexFeectivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentComplexFeectivity";

    protected ArrayList<PaymentItemInfo> mSelectInfos = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mZhekous = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_complex_fee);
        aquireDatas();
        initViews();
        requestLoadData();
    }

    protected abstract void aquireDatas();

    protected abstract int getSource();

    protected void clickSelectInfos() {
        final ArrayList<PaymentItemInfo> datas = mSelectInfos;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectInfos: info: " + info);
                requestRelativeData(mRoot, info);
            }
        });
    }

    protected void clickSelectZhekous() {
        final ArrayList<PaymentItemInfo> datas = mZhekous;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectZhekous: info: " + info);
                setupSelectedUI(mRoot, info);
            }
        });
    }
    
    abstract protected void requestRelativeData(View root, PaymentItemInfo info);
    abstract protected void setupSelectedUI(View root, PaymentItemInfo info);
    
    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case R.id.detail:
            clickDetail();
            break;
        case R.id.select_info:
            clickSelectInfos();
            break;
        case R.id.dazhe_info:
            clickSelectZhekous();
            break;
        case R.id.next_step:
            clickNextStep();
            break;
        }
    }

    private void clickDetail() {
        gotoSubActivity(getSource(),
                PaymentComplexFeeDetailListActivity.class);
    }
    
    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }

    protected abstract PaymentSubmitBean createFeedan();
    
    private void clickNextStep() {
        mItemInfo = createFeedan();
        gotoSubActivity(mItemInfo, PaymentCommonConfirmActivity.class);
    }
    
    
}
