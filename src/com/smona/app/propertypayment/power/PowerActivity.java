package com.smona.app.propertypayment.power;

import java.lang.reflect.Type;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.submit.PaymentPowerSubmitBean;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.simple.PaymentSimpleActivity;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleMessageProcessProxy;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.power.bean.PaymentPowerCompanyBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerCompanyListBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerRequestUserQueryBean;
import com.smona.app.propertypayment.power.bean.PaymentPowerQueryUserBean;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PowerActivity extends PaymentSimpleActivity {

    @Override
    protected void initTitle() {
        initText(R.id.title, R.string.payment_home_power);
    }

    @Override
    protected void initOriNo() {
        View parent = mRoot.findViewById(R.id.select_city);
        parent = mRoot.findViewById(R.id.select_company);
        initTextHint(parent, R.id.select_type,
                R.string.payment_power_select_company);
        initText(parent, R.id.select_type_value, R.string.payment_power_company);
        initView(R.id.select_company);
    }

    @Override
    protected void parseData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentSimpleCodeConstants.MSG_POWER_COMPANY_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPowerCompanyListBean>() {
                }.getType();
                PaymentPowerCompanyListBean list = JsonUtils.parseJson(content,
                        type);
                mCompanyList.clear();
                if (list.icobject != null) {
                    mCompanyList.addAll(list.icobject);
                }
            } else {

            }
        } else if (PaymentSimpleCodeConstants.MSG_POWER_USER_INFO_RESPONSE
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
    }

    @Override
    protected String getCompanyRequestCode() {
        return PaymentSimpleCodeConstants.MSG_POWER_COMPANY;
    }

    @Override
    protected void verdifyData(String cityCode, String org_no, String consno) {
        PaymentRequestInfo request = new PaymentPowerRequestUserQueryBean();
        ((PaymentPowerRequestUserQueryBean) request).org_no = org_no;
        ((PaymentPowerRequestUserQueryBean) request).consno = consno;
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestUserInfo(
                this, request, this);
    }

    @Override
    protected PaymentSubmitBean createFeedan(PaymentItemInfo content) {
        PaymentPowerSubmitBean item = (PaymentPowerSubmitBean) content;
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

    @Override
    protected void requestData() {
        mMessageProcess = new PaymentSimpleMessageProcessProxy();
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestCity(
                PaymentSimpleCodeConstants.MSG_CITY, this, this);
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

    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_POWER;
    }

    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }
}
