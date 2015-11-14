package com.smona.app.propertypayment.common.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailsBean;
import com.smona.app.propertypayment.park.process.PaymentParkMessageProcessProxy;
import com.smona.app.propertypayment.power.bean.PaymentPowerDetailsBean;
import com.smona.app.propertypayment.power.process.PaymentPowerMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentSimpleFeeDetailListActivity extends
        PaymentFetchListActivity {
    
    private static final String TAG = "PaymentSimpleFeeDetailListActivity"; 

    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    private int mSourceType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee_detail_list);
        acquireItemInfo();
        initViews();
        requestLoadData();
    }

    private void acquireItemInfo() {
        mSourceType = getIntent().getIntExtra(PaymentConstants.DATA_SOURCE, -1);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_detail_list);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        setFetchListener(mShowDatas);
    }
    
    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();
        PaymentRequestInfo request = new PaymentRequestInfo();
        if (mSourceType == PaymentConstants.DATA_SOURCE_POWER) {
            mMessageProcess = new PaymentPowerMessageProcessProxy();
            ((PaymentPowerMessageProcessProxy) mMessageProcess)
                    .requestDetail(this, request, this);
        } else if (mSourceType == PaymentConstants.DATA_SOURCE_GAS) {
        } else {
            hideCustomProgressDialog();
        }
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentPowerMessageProcessProxy.MSG_POWER_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerDetailsBean>() {
                }.getType();
                PaymentPowerDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                mAllDatas.addAll(detailsBean.icobject);
                requestRefreshUI();
            } else {

            }
        } else if (PaymentParkMessageProcessProxy.MSG_PARK_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentParkDetailsBean>() {
                }.getType();
                PaymentParkDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                mAllDatas.addAll(detailsBean.icobject);
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
        }
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public PaymentBaseDataAdapter createAdapter(ArrayList<PaymentItemInfo> data) {
        return new PaymentSimpleFeeListAdapter(this, data);
    }

}
