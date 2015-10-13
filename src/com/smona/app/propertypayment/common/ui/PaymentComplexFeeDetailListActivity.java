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
import com.smona.app.propertypayment.park.bean.PaymentParkCheweiBean;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailRequestInfo;
import com.smona.app.propertypayment.park.bean.PaymentParkDetailsBean;
import com.smona.app.propertypayment.park.process.PaymentParkMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDetailRequestInfo;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDetailsBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyFangchanBean;
import com.smona.app.propertypayment.property.process.PaymentPropertyMessageProcessProxy;

public class PaymentComplexFeeDetailListActivity extends
        PaymentFetchListActivity {

    private static final String TAG = "PaymentComplexFeeDetailListActivity";

    private PaymentPropertyDetailsBean mDetailsBean;
    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee_detail_list);
        acquireItemInfo();
        initViews();
        requestLoadData();
    }

    private void acquireItemInfo() {
        mItemInfo = getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_detail_list);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        setFetchListener(mAllDatas);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();
        PaymentRequestInfo request = null;
        if (mItemInfo instanceof PaymentPropertyFangchanBean) {
            request = new PaymentPropertyDetailRequestInfo();

            mMessageProcess = new PaymentPropertyMessageProcessProxy();
            ((PaymentPropertyMessageProcessProxy) mMessageProcess)
                    .requestDetail(this, request, this);
        } else if (mItemInfo instanceof PaymentParkCheweiBean) {
            request = new PaymentParkDetailRequestInfo();
            mMessageProcess = new PaymentParkMessageProcessProxy();
            ((PaymentParkMessageProcessProxy) mMessageProcess).requestDetail(
                    this, request, this);
        } else {
            hideCustomProgressDialog();
        }
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "content: " + content);
        if ("0510".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPropertyDetailsBean>() {
                }.getType();
                mDetailsBean = JsonUtils.parseJson(content, type);
                mAllDatas.addAll(mDetailsBean.icobject);
                requestRefreshUI();
            } else {

            }
        } else if ("1010".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentParkDetailsBean>() {
                }.getType();
                mDetailsBean = JsonUtils.parseJson(content, type);
                mAllDatas.addAll(mDetailsBean.icobject);
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
        PaymentComplexFeeListAdapter adapter = new PaymentComplexFeeListAdapter(
                this, data);
        adapter.setSource(getIntent().getIntExtra(PaymentConstants.DATA_SOURCE,
                -1));
        return adapter;
    }

}
