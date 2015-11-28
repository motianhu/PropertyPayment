package com.smona.app.propertypayment.heat;

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
import com.smona.app.propertypayment.common.data.submit.PaymentHeatSubmitBean;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentComplexFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCityBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCityListBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCompanyBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatCompanyListBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatQueryUserBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatRequestCompanyBean;
import com.smona.app.propertypayment.heat.bean.PaymentHeatRequestUserQueryBean;
import com.smona.app.propertypayment.heat.process.PaymentHeatMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class HeatActivity extends PaymentBaseActivity {
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
        initText(R.id.title, R.string.payment_home_heat);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_city);
        initTextHint(parent, R.id.select_type, R.string.payment_heat_company);
        initText(parent, R.id.select_type_value, R.string.payment_power_city);
        initView(R.id.select_city);

        parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_heat_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_heat_company);
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

        mMessageProcess = new PaymentHeatMessageProcessProxy();
        ((PaymentHeatMessageProcessProxy) mMessageProcess).requestCity(this,
                this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentHeatMessageProcessProxy.MSG_POWER_CITY_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatCityListBean>() {
                }.getType();
                PaymentHeatCityListBean list = JsonUtils.parseJson(content,
                        type);
                mCityList.clear();
                if (list.icobject != null) {
                    mCityList.addAll(list.icobject);
                }
            } else {

            }
        } else if (PaymentHeatMessageProcessProxy.MSG_POWER_COMPANY_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatCompanyListBean>() {
                }.getType();
                PaymentHeatCompanyListBean list = JsonUtils.parseJson(content,
                        type);
                mCompanyList.clear();
                if (list.icobject != null) {
                    mCompanyList.addAll(list.icobject);
                }
            } else {

            }
        } else if (PaymentHeatMessageProcessProxy.MSG_POWER_USER_INFO_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentHeatQueryUserBean>() {
                }.getType();
                PaymentHeatQueryUserBean item = JsonUtils.parseJson(content,
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
        return new PaymentHeatTypeAdapter(this, datas);
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
                        ((PaymentHeatCompanyBean) info).org_name);
            }
        });
    }

    protected void requestRelativeData(View root, PaymentItemInfo source) {
        // refresh ui
        PaymentHeatCityBean city = (PaymentHeatCityBean) source;
        boolean noChange = relativeDataForUI(R.id.select_city, source,
                city.cityname);
        if (noChange) {
            return;
        }

        relativeDataForUI(R.id.select_company, null, null);

        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentHeatRequestCompanyBean();
        ((PaymentHeatRequestCompanyBean) request).citycode = city.citycode;
        ((PaymentHeatMessageProcessProxy) mMessageProcess).requestCompany(this,
                request, this);
    }

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
        if (!(city instanceof PaymentHeatCityBean)) {
            showMessage("请选择所属地市");
            return;
        }

        Object company = getTag(R.id.select_company);
        if (!(company instanceof PaymentHeatCompanyBean)) {
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

        verdifyData("", ((PaymentHeatCompanyBean) company).org_no, housecode);
    }

    private void verdifyData(String cityCode, String org_no, String consno) {
        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentHeatRequestUserQueryBean();
        ((PaymentHeatRequestUserQueryBean) request).org_no = org_no;
        ((PaymentHeatRequestUserQueryBean) request).consno = consno;
        ((PaymentHeatMessageProcessProxy) mMessageProcess).requestUserInfo(
                this, request, this);
    }

    private void gotoNextStep(PaymentHeatQueryUserBean item) {
        PaymentSubmitBean fee = createFeedan(item);
        gotoSubActivity(fee, PaymentHeatFeeActivity.class);
    }

    protected PaymentSubmitBean createFeedan(PaymentHeatQueryUserBean item) {

        PaymentHeatSubmitBean pay = new PaymentHeatSubmitBean();

        View parent = mRoot.findViewById(R.id.select_company);
        PaymentHeatCompanyBean company = (PaymentHeatCompanyBean) getTag(
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
        gotoSubActivity(getSource(), PaymentComplexFeeDetailListActivity.class);
    }

    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_HEAT;
    }

    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }

}
