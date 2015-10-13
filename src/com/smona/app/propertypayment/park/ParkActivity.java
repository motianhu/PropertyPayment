package com.smona.app.propertypayment.park;

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
import com.smona.app.propertypayment.park.bean.PaymentParkBean;
import com.smona.app.propertypayment.park.process.PaymentParkMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;
import com.smona.app.propertypayment.park.PaymentParkTypeAdapter;
import com.smona.app.propertypayment.park.bean.PaymentParkDiscountRequestInfo;
import com.smona.app.propertypayment.park.bean.PaymentParkCheweiBean;
import com.smona.app.propertypayment.park.bean.PaymentParkCheweisBean;
import com.smona.app.propertypayment.park.bean.PaymentParkPlanRequestInfo;

public class ParkActivity extends PaymentComplexFeectivity {
    private final static String TAG = "ParkActivity";

    private PaymentParkBean mParkBean;

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_park);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_park_select_cheliang_info);
        initText(parent, R.id.select_type_value,
                R.string.payment_park_cheliang_info);
        initView(R.id.select_info);

        parent = mRoot.findViewById(R.id.property_company);
        initText(parent, R.id.name, R.string.payment_park_company);

        parent = mRoot.findViewById(R.id.yingjiao_jine);
        initText(parent, R.id.name, R.string.payment_park_yingjiao_jine);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_park_dazhe_info_hint);
        initText(parent, R.id.select_type_value,
                R.string.payment_park_dazhe_info);
        initView(R.id.dazhe_info);

        parent = mRoot.findViewById(R.id.yucun_jine);
        initText(parent, R.id.name, R.string.payment_park_yucun_info);
        initText(parent, R.id.value, 1000 + "元");

        parent = mRoot.findViewById(R.id.heji_jine);
        initText(parent, R.id.name, R.string.payment_park_heji_info);
        initText(parent, R.id.value, 1560.5 + "元");

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
    }

    @Override
    protected void aquireDatas() {
    }

    protected int getSource() {
        return PaymentConstants.DATA_SOURCE_PARK;
    }

    protected PaymentTypeAdapter createTypeAdapter(
            ArrayList<PaymentItemInfo> datas) {
        return new PaymentParkTypeAdapter(this, datas);
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();

        mParkBean = new PaymentParkBean();

        mMessageProcess = new PaymentParkMessageProcessProxy();
        ((PaymentParkMessageProcessProxy) mMessageProcess).requestChewei(this,
                this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "content: " + content);
        if ("0710".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentParkCheweisBean>() {
                }.getType();
                mItemInfo = JsonUtils.parseJson(content, type);
                mSelectInfos.clear();
                mSelectInfos
                        .addAll(((PaymentParkCheweisBean) mItemInfo).icobject);
            } else {

            }
            hideCustomProgressDialog();
        } else if ("0810".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentDiscountsBean>() {
                }.getType();
                mParkBean.mDiscountBean = JsonUtils.parseJson(content, type);

                mZhekous.clear();
                mZhekous.addAll(mParkBean.mDiscountBean.icobject);
            } else {
                hideCustomProgressDialog();
            }
        } else if ("0910".equals(bean.iccode)) {
            if (isRequestOk(bean)) {
                type = new TypeToken<PaymentPayPlanBean>() {
                }.getType();
                mParkBean.mPlanBean = JsonUtils.parseJson(content, type);
            } else {
                hideCustomProgressDialog();
            }
        }

        boolean finishInit = mParkBean.finishInit();
        if (finishInit) {
            requestRefreshUI();
            hideCustomProgressDialog();
        }
    }

    protected void refreshUI() {
        PaymentPayPlanBean plan = mParkBean.mPlanBean;

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

    protected void requestRelativeData(View root, PaymentItemInfo source) {
        // refresh ui
        PaymentParkCheweiBean chewei = (PaymentParkCheweiBean) source;

        View parent = mRoot.findViewById(R.id.select_info);
        initText(parent, R.id.select_type, chewei.getCarInfo());
        setTag(R.id.select_info, chewei);

        parent = mRoot.findViewById(R.id.property_company);
        initText(parent, R.id.value, chewei.propertyname);

        // loading relative data;
        showCustomProgrssDialog();

        PaymentRequestInfo request = new PaymentParkDiscountRequestInfo();
        ((PaymentParkDiscountRequestInfo) request).communitycode = chewei.communitycode;
        ((PaymentParkMessageProcessProxy) mMessageProcess).requestDiscount(
                this, request, this);

        request = new PaymentParkPlanRequestInfo();
        ((PaymentParkPlanRequestInfo) request).communitycode = chewei.communitycode;
        ((PaymentParkPlanRequestInfo) request).parknum = chewei.parknum;
        ((PaymentParkMessageProcessProxy) mMessageProcess).requestPlan(
                this, request, this);
    }

    protected void setupSelectedUI(View root, PaymentItemInfo info) {
        // refresh ui
        PaymentDiscountBean discount = (PaymentDiscountBean) info;

        View parent = root.findViewById(R.id.dazhe_info);
        initText(parent, R.id.select_type, discount.getDiscountName(this));
        setTag(R.id.dazhe_info, info);
    }
}
