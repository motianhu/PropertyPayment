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
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCompanyBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleCompanyListBean;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleQueryUserBean;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.power.bean.PaymentPowerQueryUserBean;

public class PowerActivity extends PaymentSimpleActivity {
    protected static final String TAG = "PowerActivity";

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
                type = new TypeToken<PaymentSimpleCompanyListBean>() {
                }.getType();
                PaymentSimpleCompanyListBean list = JsonUtils.parseJson(
                        content, type);
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
    protected String getVerdifyRequestCode() {
        return PaymentSimpleCodeConstants.MSG_POWER_USER_INFO;
    }

    @Override
    protected PaymentSubmitBean createFeedan(PaymentItemInfo content) {
        PaymentSimpleQueryUserBean item = (PaymentSimpleQueryUserBean) content;
        PaymentPowerSubmitBean pay = new PaymentPowerSubmitBean();

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

    protected PaymentSubmitBean createFeedan(PaymentPowerQueryUserBean item) {

        PaymentPowerSubmitBean pay = new PaymentPowerSubmitBean();

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

    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_POWER;
    }

    protected Intent createIntent() {
        Intent intent = new Intent();
        return intent;
    }
}
