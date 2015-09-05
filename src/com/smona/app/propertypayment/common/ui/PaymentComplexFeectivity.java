package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentFeeDanInfo;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.PaymentTypeItem;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.util.LogUtil;

public abstract class PaymentComplexFeectivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentComplexFeectivity";

    protected ArrayList<PaymentItemInfo> mSelectInfos = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mZhekous = new ArrayList<PaymentItemInfo>();

    private PaymentFeeDanInfo mFeeDan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_complex_fee);
        aquireDatas();
        initViews();
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
                View parent = mRoot.findViewById(R.id.select_info);
                initText(parent, R.id.select_type,
                        ((PaymentTypeItem) info).type_name);
                setTag(R.id.select_type, info);
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
                View parent = mRoot.findViewById(R.id.dazhe_info);
                initText(parent, R.id.select_type,
                        ((PaymentTypeItem) info).type_name);
                setTag(R.id.select_type, info);
            }
        });
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
            gotoSubActivity(getSource(),
                    PaymentComplexFeeDetailListActivity.class);
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

    private void clickNextStep() {

        mFeeDan = new PaymentFeeDanInfo();
        mFeeDan.companycode = "1";
        mFeeDan.companyname = "company 1";
        mFeeDan.money = 100;

        gotoSubActivity(mFeeDan, PaymentConfirmActivity.class);
    }
}
