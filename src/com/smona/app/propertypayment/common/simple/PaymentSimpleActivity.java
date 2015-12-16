package com.smona.app.propertypayment.common.simple;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCityBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCityListBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCompanyBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleQueryCompanyBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleFeeInfoBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleMessageProcessProxy;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentComplexFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public abstract class PaymentSimpleActivity extends PaymentBaseActivity {
    protected static String TAG = "PaymentSimpleActivity";

    protected ArrayList<PaymentItemInfo> mCityList = new ArrayList<PaymentItemInfo>();
    protected ArrayList<PaymentItemInfo> mCompanyList = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_simple);
        aquireDatas();
        initViews();
        requestLoadData();
    }

    private void aquireDatas() {

    }

    protected void initHeader() {
        initTitle();
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    protected abstract void initTitle();

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_city);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_city);
        initText(parent, R.id.select_type_value, R.string.payment_power_city);
        initView(R.id.select_city);

        initOriNo();

        parent = mRoot.findViewById(R.id.input_huhao);
        initTextHint(parent, R.id.value, R.string.payment_power_input_huhao);
        initText(parent, R.id.name, R.string.payment_power_huhao);
        initView(R.id.input_huhao);

        initText(R.id.next_step, R.string.payment_common_next_step);
        initView(R.id.next_step);
    }

    protected abstract void initOriNo();

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        showCustomProgrssDialog();
        mMessageProcess = new PaymentSimpleMessageProcessProxy();
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestCity(
                PaymentSimpleCodeConstants.MSG_CITY, this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentSimpleCodeConstants.MSG_CITY_RESPONSE.equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentSimpleCityListBean>() {
                }.getType();
                PaymentSimpleCityListBean list = JsonUtils.parseJson(content,
                        type);
                mCityList.clear();
                if (list.icobject != null) {
                    mCityList.addAll(list.icobject);
                }
            } else {

            }
        } else {
            parseData(content);
        }

        hideCustomProgressDialog();
    }

    protected abstract void parseData(String content);

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {

    }

    protected PaymentTypeAdapter createTypeAdapter(
            ArrayList<PaymentItemInfo> datas) {
        return new PaymentSimpleTypeAdapter(this, datas);
    }

    protected void clickSelectCity() {
        final ArrayList<PaymentItemInfo> datas = mCityList;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectCity: " + info);
                requestRelativeData(mRoot, info);
            }
        });
    }

    protected void clickSelectCompany() {
        final ArrayList<PaymentItemInfo> datas = mCompanyList;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectCompany: " + info);
                relativeDataForUI(R.id.select_company, info,
                        ((PaymentSimpleCompanyBean) info).org_name);
            }
        });
    }

    protected void requestRelativeData(View root, PaymentItemInfo source) {
        // refresh ui
        PaymentSimpleCityBean city = (PaymentSimpleCityBean) source;
        boolean noChange = relativeDataForUI(R.id.select_city, source,
                city.cityname);
        if (noChange) {
            return;
        }

        relativeDataForUI(R.id.select_company, null, null);

        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentSimpleQueryCompanyBean();
        ((PaymentSimpleQueryCompanyBean) request).citycode = city.citycode;
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestCompany(
                getCompanyRequestCode(), this, request, this);
    }

    protected abstract String getCompanyRequestCode();

    private boolean relativeDataForUI(int resId, PaymentItemInfo source,
            String name) {
        View parent = mRoot.findViewById(resId);
        initText(parent, R.id.select_type, name);
        Object obj = getTag(resId);
        setTag(resId, source);
        return obj == source;
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
            clickDetail();
            break;
        case R.id.select_city:
            clickSelectCity();
            break;
        case R.id.select_company:
            clickSelectCompany();
            break;
        case R.id.next_step:
            clickNextStep();
            break;
        }

    }

    private void clickNextStep() {
        Object city = getTag(R.id.select_city);
        if (!(city instanceof PaymentSimpleCityBean)) {
            showMessage("请选择所属地市");
            return;
        }

        Object company = getTag(R.id.select_company);
        if (!(company instanceof PaymentSimpleCompanyBean)) {
            showMessage("请选择供电单位");
            return;
        }

        View parent = mRoot.findViewById(R.id.input_huhao);
        String housecode = ((TextView) parent.findViewById(R.id.value))
                .getText().toString();
        if (TextUtils.isEmpty(housecode)) {
            showMessage("请选择输入户号");
            return;
        }

        showCustomProgrssDialog();
        verdifyData("", ((PaymentSimpleCompanyBean) company).org_no, housecode);
    }

    protected void verdifyData(String cityCode, String org_no, String consno) {
        PaymentRequestInfo request = createQueryFeeInfo(org_no, consno);
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestUserInfo(
                getVerdifyRequestCode(), this, request, this);
    }

    protected abstract PaymentRequestInfo createQueryFeeInfo(String org_no, String consno);

    protected abstract String getVerdifyRequestCode();

    private void clickDetail() {
        gotoSubActivity(getSource(), PaymentComplexFeeDetailListActivity.class);
    }

    protected abstract int getSource();

    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }

    protected void gotoNextStep(PaymentSimpleFeeInfoBean item) {
        PaymentSubmitBean fee = createFeedan(item);
        gotoSubActivity(fee, getSource(), getSubActivityClass());
    }
    
    protected abstract Class<?> getSubActivityClass();

    protected PaymentSubmitBean createFeedan(PaymentSimpleFeeInfoBean item) {
        PaymentSimpleSubmitBean pay = new PaymentSimpleSubmitBean();

        View parent = mRoot.findViewById(R.id.select_company);
        PaymentSimpleCompanyBean company = (PaymentSimpleCompanyBean) getTag(
                parent, R.id.select_company);

        parent = mRoot.findViewById(R.id.input_huhao);
        String housecode = ((TextView) parent.findViewById(R.id.value))
                .getText().toString();
        pay.consno = housecode;
        pay.trans_name = item.trans_name;

        pay.org_no = company.org_no;
        pay.org_name = company.org_name;

        pay.exchg_atm = item.exchg_atm;
        pay.postradeno = item.postradeno;
        pay.accountdate = item.postradeno;

        return pay;
    }
}
