package com.smona.app.propertypayment.common.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleQueryDetailBean;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleMessageProcessProxy;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.heat.bean.PaymentHeatDetailsBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerDetailsBean;

public class PaymentComplexDetailListActivity extends PaymentFetchListActivity {

    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    private int mSourceType;
    private PaymentSimpleQueryDetailBean mQueryDetailInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_complex_detail_list);
        acquireItemInfo();
        initViews();
    }

    private void acquireItemInfo() {
        mSourceType = this.getIntent().getIntExtra(
                PaymentConstants.DATA_SOURCE, -1);
        mQueryDetailInfo = new PaymentSimpleQueryDetailBean();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_detail_list);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        initView(R.id.query);
        setFetchListener(mShowDatas);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();
        String queryCode = PaymentSimpleCodeConstants.MSG_POWER_DETAIL;
        if (mSourceType == PaymentConstants.DATA_SOURCE_HEAT) {
            queryCode = PaymentSimpleCodeConstants.MSG_HEAT_DETAIL;
        } else if (mSourceType == PaymentConstants.DATA_SOURCE_WATER) {
            queryCode = PaymentSimpleCodeConstants.MSG_WATER_DETAIL;
        } else if (mSourceType == PaymentConstants.DATA_SOURCE_GAS) {
            queryCode = PaymentSimpleCodeConstants.MSG_GAS_DETAIL;
        }
        mMessageProcess = new PaymentSimpleMessageProcessProxy();
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestDetail(
                queryCode, this, mQueryDetailInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentSimpleCodeConstants.MSG_POWER_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerDetailsBean>() {
                }.getType();
                PaymentPowerDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        } else if (PaymentSimpleCodeConstants.MSG_HEAT_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatDetailsBean>() {
                }.getType();
                PaymentHeatDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        } else if (PaymentSimpleCodeConstants.MSG_WATER_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatDetailsBean>() {
                }.getType();
                PaymentHeatDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        } else if (PaymentSimpleCodeConstants.MSG_GAS_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatDetailsBean>() {
                }.getType();
                PaymentHeatDetailsBean detailsBean = JsonUtils.parseJson(
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
        if (R.id.back == id) {
            finish();
            return;
        } else if(R.id.query == id) {
            TextView text = (TextView)findViewById(R.id.huhao);
            if(TextUtils.isEmpty(text.getText())) {
                showMessage(R.string.payment_water_input_huhao);
                return;
            }
            mQueryDetailInfo.con_so = text.getText().toString();
            requestLoadData();
        }
        
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public PaymentBaseDataAdapter createAdapter(ArrayList<PaymentItemInfo> data) {
        PaymentBaseDataAdapter adapter;
        if (mSourceType == PaymentConstants.DATA_SOURCE_NONTAX) {
            adapter = new PaymentSimpleListSixAdapter(this, data);
        } else {
            adapter = new PaymentSimpleListAdapter(this, data);
        }
        return adapter;
    }

}
