package com.smona.app.propertypayment.heat;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.submit.PaymentHeatSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseDataAdapter;
import com.smona.app.propertypayment.common.ui.PaymentFetchListActivity;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.heat.bean.PaymentHeatDetailItemsBean;
import com.smona.app.propertypayment.heat.process.PaymentHeatMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentHeatFeeActivity extends PaymentFetchListActivity {

    private static final String TAG = PaymentHeatFeeActivity.class
            .getSimpleName();

    protected PaymentHeatSubmitBean mFeeDan;
    
    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_heat_fee_activity);
        acquireItemInfo();
        initViews();
        requestLoadData();
    }

    private void acquireItemInfo() {
        mFeeDan = (PaymentHeatSubmitBean) getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
        LogUtil.d(TAG, "acquireItemInfo mItem: " + mFeeDan);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_heat);
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
        
        initText(R.id.qianfei, R.string.payment_heat_qianfei_time);
        initText(R.id.danjia, R.string.payment_heat_danjia);
        initText(R.id.zhinajin, R.string.payment_heat_zhinajin);
        initText(R.id.yingjiao, R.string.payment_heat_yingjiao);
        
        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
        
        setFetchListener(mShowDatas);
    }
    
    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();
        PaymentRequestInfo request = new PaymentRequestInfo();
        mMessageProcess = new PaymentHeatMessageProcessProxy();
        ((PaymentHeatMessageProcessProxy) mMessageProcess).requestList(this,
                request, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentHeatMessageProcessProxy.MSG_HEAT_QIANFEI_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatDetailItemsBean>() {
                }.getType();
                PaymentHeatDetailItemsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        }
        hideCustomProgressDialog();
    }

    protected void refreshUI() {
        mShowDatas.clear();
        mShowDatas.addAll(mAllDatas);

        notifyDataSetChanged();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
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
        mFeeDan.transfare = mFeeDan.exchg_atm;
        gotoSubActivity(mFeeDan, PaymentHeatConfirmActivity.class);
    }

    @Override
    protected void loadMore() {
        
    }

    @Override
    public PaymentBaseDataAdapter createAdapter(ArrayList<PaymentItemInfo> data) {
        return new PaymentHeatDetailAdapter(this, mShowDatas);
    }
}
