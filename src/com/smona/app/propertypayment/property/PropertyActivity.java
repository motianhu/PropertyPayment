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
import com.smona.app.propertypayment.property.bean.PaymentPropertyPlanRequestInfo;
import com.smona.app.propertypayment.property.process.PaymentPropertyMessageProcessProxy;

public class PropertyActivity extends PaymentComplexFeectivity {
    private final static String TAG = "PropertyActivity";

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
        initText(parent, R.id.value, 1000 + "元");

        parent = mRoot.findViewById(R.id.heji_jine);
        initText(parent, R.id.name, R.string.payment_property_heji_info);
        initText(parent, R.id.value, 1560.5 + "元");

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
        mItemInfo = new PaymentPropertyBean();
        mMessageProcess = new PaymentPropertyMessageProcessProxy();
        ((PaymentPropertyMessageProcessProxy) mMessageProcess).requestFangchan(
                this, this);
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
                mSelectInfos.addAll(fangchans.icobject);
            } else {

            }
            hideCustomProgressDialog();
        } else if ("0310".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentDiscountsBean>() {
                }.getType();
                ((PaymentPropertyBean) mItemInfo).mDiscountBean = JsonUtils
                        .parseJson(content, type);

                mZhekous.clear();
                mZhekous.addAll(((PaymentPropertyBean) mItemInfo).mDiscountBean.icobject);
            } else {
                hideCustomProgressDialog();
            }
        } else if ("0410".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPayPlanBean>() {
                }.getType();
                ((PaymentPropertyBean) mItemInfo).mPlanBean = JsonUtils
                        .parseJson(content, type);
            } else {
                hideCustomProgressDialog();
            }
        }

        boolean finishInit = ((PaymentPropertyBean) mItemInfo).finishInit();
        if (finishInit) {
            requestRefreshUI();
            hideCustomProgressDialog();
        }
    }

    protected void refreshUI() {
        PaymentPayPlanBean plan = ((PaymentPropertyBean) mItemInfo).mPlanBean;

        View parent = mRoot.findViewById(R.id.yingjiao_jine);
        initText(
                parent,
                R.id.value,
                plan.needfare
                        + getResources().getString(R.string.payment_common_rmb));
        initText(parent, R.id.description, plan.needdscrp);
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }
}
