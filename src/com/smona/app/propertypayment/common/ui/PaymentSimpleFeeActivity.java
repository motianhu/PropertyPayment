package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.PaymentSimpleDetailInfo;
import com.smona.app.propertypayment.common.data.PaymentTypeItem;
import com.smona.app.propertypayment.common.util.LogUtil;

public abstract class PaymentSimpleFeeActivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentSimpleFeeActivity";

    protected ArrayList<PaymentItemInfo> mPayCompanys = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mGroups = new ArrayList<PaymentItemInfo>();

    private PaymentSimpleDetailInfo mContent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple_fee);
        initViews();
        initData();
    }

    private void initData() {
        initContent();
        initCompanys();
        initGroupDatas();
    }

    private void initContent() {
        mContent = new PaymentSimpleDetailInfo();
    }

    private void initGroupDatas() {
        String[] groupCodes = this.getResources().getStringArray(
                R.array.group_code);
        String[] groupNames = this.getResources().getStringArray(
                R.array.group_name);
        int size = groupCodes.length;
        for (int i = 0; i < size; i++) {
            PaymentTypeItem type = new PaymentTypeItem();
            type.type_id = groupCodes[i];
            type.type_name = groupNames[i];
            mGroups.add(type);
        }
    }

    protected abstract void initCompanys();

    protected void clickSelectCompany() {
        final ArrayList<PaymentItemInfo> datas = mPayCompanys;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectCompany: info: " + info);
                View parent = mRoot.findViewById(R.id.select_company);
                initText(parent, R.id.select_type,
                        ((PaymentTypeItem) info).type_name);
                setTag(R.id.select_company, info);
                mContent.companycode = ((PaymentTypeItem) info).type_id;
                mContent.companyname = ((PaymentTypeItem) info).type_name;
            }
        });
    }

    protected void clickSelectGroup() {
        final ArrayList<PaymentItemInfo> datas = mGroups;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectGroup: info: " + info);
                View parent = mRoot.findViewById(R.id.select_groupby);
                initText(parent, R.id.select_type,
                        ((PaymentTypeItem) info).type_name);
                setTag(R.id.select_groupby, info);
                mContent.groudcode = ((PaymentTypeItem) info).type_id;
                mContent.groudname = ((PaymentTypeItem) info).type_name;
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
            gotoSubActivity(PaymentSimpleFeeDetailListActivity.class);
            break;
        case R.id.select_company:
            clickSelectCompany();
            break;
        case R.id.select_groupby:
            clickSelectGroup();
            break;
        case R.id.next_step:
            clickNextStep();
            break;
        }
    }

    private void clickNextStep() {
        Object company = getTag(R.id.select_company);
        if (!(company instanceof PaymentTypeItem)) {
            showMessage("请选择缴费单位");
            return;
        }
        View parent = mRoot.findViewById(R.id.input_huhao);
        String housecode = ((TextView) parent.findViewById(R.id.value))
                .getText().toString();
        if (TextUtils.isEmpty(housecode)) {
            showMessage("请选择输入户号");
            return;
        }
        Object group = getTag(R.id.select_groupby);
        if (!(group instanceof PaymentTypeItem)) {
            showMessage("请选择分组");
            return;
        }

        mContent.housecode = housecode;

        TextView text = (TextView) mRoot.findViewById(R.id.title);
        gotoSubActivity(text.getText().toString(), mContent,
                PaymentSimpleFeePayActivity.class);
    }

}
