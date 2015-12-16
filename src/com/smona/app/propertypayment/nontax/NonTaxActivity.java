package com.smona.app.propertypayment.nontax;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentBaseDataAdapter;
import com.smona.app.propertypayment.common.ui.PaymentSimpleDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentFetchListActivity;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.nontax.bean.PaymentNonTaxItemBean;
import com.smona.app.propertypayment.nontax.bean.PaymentNonTaxItemsBean;
import com.smona.app.propertypayment.nontax.bean.PaymentQueryNonTaxInfo;
import com.smona.app.propertypayment.nontax.process.PaymentNonTaxMessageProcessProxy;

public class NonTaxActivity extends PaymentFetchListActivity {

    protected ArrayList<PaymentItemInfo> mAllDatas = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mShowDatas = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_nontax);
        aquireDatas();
        initViews();
        requestLoadData();
    }

    protected void aquireDatas() {

    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_nontax);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.desc_container);
        initText(parent, R.id.kemu, R.string.payment_nontax_kemu);
        initText(parent, R.id.leibie, R.string.payment_nontax_leibie);
        initText(parent, R.id.jine, R.string.payment_nontax_jine);
        initText(parent, R.id.bank, R.string.payment_nontax_bank);
        initText(parent, R.id.status, R.string.payment_nontax_status);

        parent = mRoot.findViewById(R.id.select_all);
        initText(parent, R.id.select, R.string.payment_nontax_select_all);
        initView(R.id.select);

        parent = mRoot.findViewById(R.id.jiaofei);
        initText(parent, R.id.next_step, R.string.payment_payment);
        initView(R.id.next_step);

        setFetchListener(mShowDatas);
        setOnItemClickListener(mOnItemClick);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();
        PaymentQueryNonTaxInfo request = new PaymentQueryNonTaxInfo();
        request.logintype = "2";
        mMessageProcess = new PaymentNonTaxMessageProcessProxy();
        ((PaymentNonTaxMessageProcessProxy) mMessageProcess).requestList(this,
                request, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentNonTaxMessageProcessProxy.MSG_NONTAX_LIST_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentNonTaxItemsBean>() {
                }.getType();
                PaymentNonTaxItemsBean detailsBean = JsonUtils.parseJson(
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
        LogUtil.d("motianhu", "v: " + v);
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case R.id.detail:
            clickDetail();
            break;
        case R.id.select:
            clickAll();
            break;
        case R.id.next_step:
            clickJiaofei();
            break;
        }
    }

    @Override
    public PaymentBaseDataAdapter createAdapter(ArrayList<PaymentItemInfo> data) {
        return new PaymentNonTaxAdapter(this, data);
    }

    @Override
    protected void loadMore() {

    }

    private void clickDetail() {
        gotoSubActivity(getSource(), PaymentSimpleDetailListActivity.class);
    }

    private int getSource() {
        return PaymentConstants.DATA_SOURCE_NONTAX;
    }

    private void clickAll() {
        if (btnIsSelectAll()) {
            clickSelectAll();
            initSelectAllText(false);
        } else {
            clickUnSelectAll();
            initSelectAllText(true);
        }
    }

    public void changeState() {
        if (btnIsSelectAll()) {
            if (isSelectAll()) {
                initSelectAllText(false);
            }
        } else {
            if (!isSelectAll()) {
                initSelectAllText(true);
            }
        }
    }

    private boolean btnIsSelectAll() {
        View parent = mRoot.findViewById(R.id.select_all);
        String content = getTextContent(parent, R.id.select);
        return getResources().getString(R.string.payment_nontax_select_all)
                .equalsIgnoreCase(content);
    }

    private void initSelectAllText(boolean selectAll) {
        View parent = mRoot.findViewById(R.id.select_all);
        initText(parent, R.id.select,
                selectAll ? R.string.payment_nontax_select_all
                        : R.string.payment_nontax_unselect_all);
    }

    private boolean isSelectAll() {
        for (PaymentItemInfo info : mShowDatas) {
            PaymentNonTaxItemBean item = (PaymentNonTaxItemBean) info;
            if (!item.isSelected) {
                return false;
            }
        }
        return true;
    }

    private void clickSelectAll() {
        for (PaymentItemInfo info : mShowDatas) {
            PaymentNonTaxItemBean item = (PaymentNonTaxItemBean) info;
            item.isSelected = true;
        }
        notifyDataSetChanged();
    }

    private void clickUnSelectAll() {
        for (PaymentItemInfo info : mShowDatas) {
            PaymentNonTaxItemBean item = (PaymentNonTaxItemBean) info;
            item.isSelected = false;
        }
        notifyDataSetChanged();
    }

    private void clickJiaofei() {
        int size = 0;
        for (PaymentItemInfo info : mShowDatas) {
            PaymentNonTaxItemBean item = (PaymentNonTaxItemBean) info;
            size = item.isSelected ? size + 1 : size;
        }
        Toast.makeText(this, "goto pay: " + size, Toast.LENGTH_SHORT).show();
    }

    private OnItemClickListener mOnItemClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            PaymentNonTaxItemBean item = (PaymentNonTaxItemBean)mShowDatas.get(position);
            item.isSelected = !item.isSelected;
            notifyDataSetChanged();
            changeState();
        }

    };
}
