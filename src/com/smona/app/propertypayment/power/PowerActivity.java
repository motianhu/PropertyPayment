package com.smona.app.propertypayment.power;

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
import com.smona.app.propertypayment.common.data.submit.PaymentPowerSubmitBean;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentComplexFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.power.bean.PaymentPowerCityBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerCityListBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerCompanyBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerCompanyListBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerRequestCompanyBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerRequestUserQueryBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerQueryUserBean;
import com.smona.app.propertypayment.power.process.PaymentPowerMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PowerActivity extends PaymentBaseActivity {
    private static final String TAG = "PowerActivity";

    private ArrayList<PaymentItemInfo> mCityList = new ArrayList<PaymentItemInfo>();
    private ArrayList<PaymentItemInfo> mCompanyList = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_power);
        aquireDatas();
        initViews();
        requestLoadData();
    }

    private void aquireDatas() {

    }

    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_power);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_city);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_city);
        initText(parent, R.id.select_type_value, R.string.payment_power_city);
        initView(R.id.select_city);

        parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_power_company);
        initView(R.id.select_company);

        parent = mRoot.findViewById(R.id.input_huhao);
        initTextHint(parent, R.id.value, R.string.payment_power_input_huhao);
        initText(parent, R.id.name, R.string.payment_power_huhao);
        initView(R.id.input_huhao);

        initText(R.id.next_step, R.string.payment_common_next_step);
        initView(R.id.next_step);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();

        mMessageProcess = new PaymentPowerMessageProcessProxy();
        ((PaymentPowerMessageProcessProxy) mMessageProcess).requestCity(this,
                this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentPowerMessageProcessProxy.MSG_POWER_CITY_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerCityListBean>() {
                }.getType();
                PaymentPowerCityListBean list = JsonUtils.parseJson(content,
                        type);
                mCityList.clear();
                mCityList.addAll(list.icobject);
            } else {

            }
        } else if (PaymentPowerMessageProcessProxy.MSG_POWER_COMPANY_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerCompanyListBean>() {
                }.getType();
                PaymentPowerCompanyListBean list = JsonUtils.parseJson(content,
                        type);
                mCompanyList.clear();
                mCompanyList.addAll(list.icobject);
            } else {

            }
        } else if (PaymentPowerMessageProcessProxy.MSG_POWER_USER_INFO_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerQueryUserBean>() {
                }.getType();
                PaymentPowerQueryUserBean item = JsonUtils.parseJson(content,
                        type);
                if ("0000".equals(item.return_code)) {
                    gotoNextStep(item);
                } else {

                }
            } else {

            }
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {

    }

    protected PaymentTypeAdapter createTypeAdapter(
            ArrayList<PaymentItemInfo> datas) {
        return new PaymentPowerTypeAdapter(this, datas);
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
                        ((PaymentPowerCompanyBean) info).org_name);
            }
        });
    }

    protected void requestRelativeData(View root, PaymentItemInfo source) {
        // refresh ui
        PaymentPowerCityBean city = (PaymentPowerCityBean) source;
        relativeDataForUI(R.id.select_city, source, city.cityname);

        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentPowerRequestCompanyBean();
        ((PaymentPowerRequestCompanyBean) request).citycode = city.citycode;
        ((PaymentPowerMessageProcessProxy) mMessageProcess).requestCompany(
                this, request, this);
    }

    private void relativeDataForUI(int resId, PaymentItemInfo source,
            String name) {
        View parent = mRoot.findViewById(resId);
        initText(parent, R.id.select_type, name);
        setTag(resId, source);
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
        if (!(city instanceof PaymentPowerCityBean)) {
            showMessage("请选择所属地市");
            return;
        }

        Object company = getTag(R.id.select_company);
        if (!(company instanceof PaymentPowerCompanyBean)) {
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

        verdifyData("", ((PaymentPowerCompanyBean) company).org_no, housecode);
    }

    private void verdifyData(String cityCode, String org_no, String consno) {
        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentPowerRequestUserQueryBean();
        ((PaymentPowerRequestUserQueryBean) request).org_no = org_no;
        ((PaymentPowerRequestUserQueryBean) request).consno = consno;
        ((PaymentPowerMessageProcessProxy) mMessageProcess).requestUserInfo(
                this, request, this);
    }

    private void gotoNextStep(PaymentPowerQueryUserBean item) {
        PaymentSubmitBean fee = createFeedan(item);
        gotoSubActivity(fee, PaymentPowerFeeActivity.class);
    }

    protected PaymentSubmitBean createFeedan(PaymentPowerQueryUserBean item) {

        PaymentPowerSubmitBean pay = new PaymentPowerSubmitBean();

        View parent = mRoot.findViewById(R.id.select_company);
        PaymentPowerCompanyBean company = (PaymentPowerCompanyBean) getTag(
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
    
    private void clickDetail() {
        gotoSubActivity(getSource(),
                PaymentComplexFeeDetailListActivity.class);
    }
    
    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_POWER;
    }
    
    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }

}
