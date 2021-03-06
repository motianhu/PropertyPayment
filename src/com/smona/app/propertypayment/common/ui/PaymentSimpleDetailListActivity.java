package com.smona.app.propertypayment.common.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.nontax.bean.PaymentNonTaxDetailsBean;
import com.smona.app.propertypayment.nontax.bean.PaymentQueryNonTaxInfo;
import com.smona.app.propertypayment.nontax.process.PaymentNonTaxMessageProcessProxy;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailsBean;
import com.smona.app.propertypayment.park.process.PaymentParkMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDetailsBean;
import com.smona.app.propertypayment.property.process.PaymentPropertyMessageProcessProxy;

public class PaymentSimpleDetailListActivity extends PaymentFetchListActivity {

    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    private int mSourceType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_detail_list);
        acquireItemInfo();
        initViews();
        requestLoadData();
    }

    private void acquireItemInfo() {
        mSourceType = this.getIntent().getIntExtra(
                PaymentConstants.DATA_SOURCE, -1);
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
        if (mSourceType == PaymentConstants.DATA_SOURCE_PROPERTY) {
            mMessageProcess = new PaymentPropertyMessageProcessProxy();
            ((PaymentPropertyMessageProcessProxy) mMessageProcess)
                    .requestDetail(this, request, this);
        } else if (mSourceType == PaymentConstants.DATA_SOURCE_PARK) {
            mMessageProcess = new PaymentParkMessageProcessProxy();
            ((PaymentParkMessageProcessProxy) mMessageProcess).requestDetail(
                    this, request, this);
        } else if (mSourceType == PaymentConstants.DATA_SOURCE_NONTAX) {
            PaymentQueryNonTaxInfo nontaxinfo = new PaymentQueryNonTaxInfo();
            nontaxinfo.logintype = "2";
            mMessageProcess = new PaymentNonTaxMessageProcessProxy();
            ((PaymentNonTaxMessageProcessProxy) mMessageProcess).requestDetail(
                    this, nontaxinfo, this);
        } else {
            hideCustomProgressDialog();
        }
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentPropertyMessageProcessProxy.MSG_PROPERTY_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPropertyDetailsBean>() {
                }.getType();
                PaymentPropertyDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        } else if (PaymentSimpleCodeConstants.MSG_PARK_PLAN_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentParkDetailsBean>() {
                }.getType();
                PaymentParkDetailsBean detailsBean = JsonUtils.parseJson(
                        content, type);
                if (detailsBean.icobject != null) {
                    mAllDatas.addAll(detailsBean.icobject);
                }
                requestRefreshUI();
            } else {

            }
        }  else if (PaymentNonTaxMessageProcessProxy.MSG_NONTAX_DETAIL_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentNonTaxDetailsBean>() {
                }.getType();
                PaymentNonTaxDetailsBean detailsBean = JsonUtils.parseJson(
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
