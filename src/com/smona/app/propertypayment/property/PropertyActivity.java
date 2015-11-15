package com.smona.app.propertypayment.property;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountBean;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountsBean;
import com.smona.app.propertypayment.common.data.payplan.PaymentPayPlanBean;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.ui.PaymentComplexFeectivity;
import com.smona.app.propertypayment.common.ui.PaymentTypeAdapter;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.process.PaymentRequestInfo;
import com.smona.app.propertypayment.property.bean.PaymentPropertyBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyDiscountRequestInfo;
import com.smona.app.propertypayment.property.bean.PaymentPropertyFangchanBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyFangchansBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyPaySubmitBean;
import com.smona.app.propertypayment.property.bean.PaymentPropertyPlanRequestInfo;
import com.smona.app.propertypayment.property.process.PaymentPropertyMessageProcessProxy;

public class PropertyActivity extends PaymentComplexFeectivity {
    private final static String TAG = "PropertyActivity";

    private PaymentPropertyBean mPropertyBean;

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_property);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_property_select_fangchan_info);
        initText(parent, R.id.select_type_value,
                R.string.payment_property_fangchan_info);
        initView(R.id.select_info);

        parent = mRoot.findViewById(R.id.property_company);
        initText(parent, R.id.name, R.string.payment_property_company);

        parent = mRoot.findViewById(R.id.yingjiao_jine);
        initText(parent, R.id.name, R.string.payment_property_yingjiao_jine);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_property_dazhe_info_hint);
        initText(parent, R.id.select_type_value,
                R.string.payment_property_dazhe_info);
        initView(R.id.dazhe_info);

        parent = mRoot.findViewById(R.id.yucun_jine);
        initText(parent, R.id.name, R.string.payment_property_yucun_info);
        initText(parent, R.id.value,
                0.0 + getResources().getString(R.string.payment_common_rmb));

        parent = mRoot.findViewById(R.id.heji_jine);
        initText(parent, R.id.name, R.string.payment_property_heji_info);
        initText(parent, R.id.value,
                0.0 + getResources().getString(R.string.payment_common_rmb));

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
    }

    @Override
    protected void aquireDatas() {
    }

    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_PROPERTY;
    }

    protected PaymentTypeAdapter createTypeAdapter(
            ArrayList<PaymentItemInfo> datas) {
        return new PaymentPropertyTypeAdapter(this, datas);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();

        mPropertyBean = new PaymentPropertyBean();

        mMessageProcess = new PaymentPropertyMessageProcessProxy();
        ((PaymentPropertyMessageProcessProxy) mMessageProcess).requestFangchan(
                this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "content: " + content);
        if ("0210".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPropertyFangchansBean>() {
                }.getType();
                PaymentPropertyFangchansBean fangchans = JsonUtils.parseJson(
                        content, type);
                mSelectInfos.clear();
                if (fangchans.icobject != null) {
                    mSelectInfos.addAll(fangchans.icobject);
                }
            } else {

            }
            hideCustomProgressDialog();
        } else if ("0310".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentDiscountsBean>() {
                }.getType();
                mPropertyBean.mDiscountBean = JsonUtils
                        .parseJson(content, type);

                mZhekous.clear();
                if (mPropertyBean.mDiscountBean.icobject != null) {
                    mZhekous.addAll(mPropertyBean.mDiscountBean.icobject);
                }
            } else {
                hideCustomProgressDialog();
            }
        } else if ("0410".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPayPlanBean>() {
                }.getType();
                mPropertyBean.mPlanBean = JsonUtils.parseJson(content, type);
            } else {
                hideCustomProgressDialog();
            }
        }

        boolean finishInit = mPropertyBean.finishInit();
        if (finishInit) {
            requestRefreshUI();
            hideCustomProgressDialog();
        }
    }

    protected void refreshUI() {
        PaymentPayPlanBean plan = mPropertyBean.mPlanBean;

        View parent = mRoot.findViewById(R.id.yingjiao_jine);
        initText(
                parent,
                R.id.value,
                plan.needfare
                        + getResources().getString(R.string.payment_common_rmb));
        initText(parent, R.id.description, plan.needdscrp);

        updateTotal(Double.valueOf(plan.needfare));
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void requestRelativeData(View root, PaymentItemInfo source) {
        // refresh ui
        PaymentPropertyFangchanBean fangchan = (PaymentPropertyFangchanBean) source;

        View parent = mRoot.findViewById(R.id.select_info);
        initText(parent, R.id.select_type, fangchan.getFangchanAddr());
        setTag(R.id.select_info, fangchan);

        parent = mRoot.findViewById(R.id.property_company);
        initText(parent, R.id.value, fangchan.propertyname);

        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentPropertyDiscountRequestInfo();
        ((PaymentPropertyDiscountRequestInfo) request).communitycode = fangchan.communitycode;
        ((PaymentPropertyMessageProcessProxy) mMessageProcess).requestDiscount(
                this, request, this);

        request = new PaymentPropertyPlanRequestInfo();
        ((PaymentPropertyPlanRequestInfo) request).communitycode = fangchan.communitycode;
        ((PaymentPropertyPlanRequestInfo) request).housingbantranscode = fangchan.housingbantranscode;
        ((PaymentPropertyMessageProcessProxy) mMessageProcess).requestPlan(
                this, request, this);
    }

    protected void setupSelectedUI(View root, PaymentItemInfo info) {
        // refresh ui
        PaymentDiscountBean discount = (PaymentDiscountBean) info;

        View parent = root.findViewById(R.id.dazhe_info);
        initText(parent, R.id.select_type, discount.getDiscountName(this));
        setTag(R.id.dazhe_info, info);

        // calc
        PaymentPayPlanBean plan = mPropertyBean.mPlanBean;

        parent = mRoot.findViewById(R.id.select_info);
        PaymentPropertyFangchanBean fangchan = (PaymentPropertyFangchanBean) getTag(
                parent, R.id.select_info);

        Double fee = Double.valueOf(fangchan.payaccount);
        int months = Integer.valueOf(discount.yearnum) * 12;
        double discountNum = Double.valueOf(discount.discount) / 100;
        double yucun = fee * months * discountNum;

        Double needfare = Double.valueOf(plan.needfare);

        // set value
        parent = root.findViewById(R.id.yucun_jine);
        initText(
                parent,
                R.id.value,
                yucun
                        + this.getResources().getString(
                                R.string.payment_common_rmb));

        updateTotal(yucun + needfare);
    }

    private void updateTotal(double fee) {
        View parent = mRoot.findViewById(R.id.heji_jine);
        initText(
                parent,
                R.id.value,
                fee
                        + this.getResources().getString(
                                R.string.payment_common_rmb));
    }

    @Override
    protected PaymentSubmitBean createFeedan() {
        PaymentPropertyPaySubmitBean pay = new PaymentPropertyPaySubmitBean();

        View parent = mRoot.findViewById(R.id.select_info);
        PaymentPropertyFangchanBean fangchan = (PaymentPropertyFangchanBean) getTag(
                parent, R.id.select_info);

        if(fangchan == null) {
            showMessage("必须选择房产信息");
            return null;
        }
        
        pay.communitycode = fangchan.communitycode;
        pay.storedfare = fangchan.payaccount;
        pay.companyname = fangchan.propertyname;
        pay.housingbantranscode = fangchan.housingbantranscode;

        parent = mRoot.findViewById(R.id.dazhe_info);
        PaymentDiscountBean discount = (PaymentDiscountBean) getTag(R.id.dazhe_info);

        if (discount != null) {
            pay.did = discount.did;
        }

        parent = mRoot.findViewById(R.id.heji_jine);
        String heji = getTextContent(parent, R.id.value);
        heji = heji.substring(0, heji.length() - 2);

        pay.needfare = heji;

        pay.pstartdate = mPropertyBean.mPlanBean.pstartdate;
        pay.penddate = mPropertyBean.mPlanBean.penddate;

        return pay;
    }

}
