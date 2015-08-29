package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentComplexListItem;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentComplexFeeDetailListActivity extends
        PaymentFetchListActivity {

    private ArrayList<PaymentItemInfo> mDatas = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee_detail_list);
        acquireItemInfo();
        initViews();
    }

    private void acquireItemInfo() {

    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_detail_list);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        for (int i = 0; i < 10; i++) {
            PaymentComplexListItem item = new PaymentComplexListItem();
            item.companyname = "company " + i;
            item.companycode = i + "";
            item.objinfo = i + "";
            item.money = i + "";
            item.paytime = "time " + i;
            
            mDatas.add(item);
        }
        setFetchListener(mDatas);
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
        return new PaymentComplexFeeListAdapter(this, data);
    }

}
