package com.smona.app.propertypayment.heat.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentHeatMessageProcessProxy extends PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;

    public static final String MSG_POWER_USER_INFO = "1200";
    public static final String MSG_POWER_SUBMIT = "1300";
    public static final String MSG_POWER_CITY = "1400";
    public static final String MSG_POWER_COMPANY = "1800";
    public static final String MSG_POWER_DETAIL = "1700";
    public static final String MSG_POWER_QIANFEI_DETAIL = "1900";

    public static final String MSG_POWER_USER_INFO_RESPONSE = "1210";
    public static final String MSG_POWER_SUBMIT_RESPONSE = "1310";
    public static final String MSG_POWER_CITY_RESPONSE = "1410";
    public static final String MSG_POWER_DETAIL_RESPONSE = "1710";
    public static final String MSG_POWER_COMPANY_RESPONSE = "1810";
    public static final String MSG_POWER_QIANFEI_DETAIL_RESPONSE = "1910";

    public PaymentHeatMessageProcessProxy() {
        mLocal = new PaymentHeatLocalMessageProcess();
        mNetRequest = new PaymentHeatNetRequestMessageProcess();
        mNetSubmit = new PaymentHeatNetSubmitMessageProcess();
    }

    public void requestCity(Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentHeatLocalMessageProcess) mLocal).requestCity(context,
                    callback);
        } else {
            ((PaymentHeatNetRequestMessageProcess) mNetRequest)
                    .requestCity(callback);
        }
    }

    public void requestCompany(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentHeatLocalMessageProcess) mLocal).requestCompany(context,
                    callback);
        } else {
            ((PaymentHeatNetRequestMessageProcess) mNetRequest)
                    .requestCompany(request, callback);
        }
    }

    public void requestUserInfo(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentHeatLocalMessageProcess) mLocal).requestUserInfo(context,
                    callback);
        } else {
            ((PaymentHeatNetRequestMessageProcess) mNetRequest)
                    .requestUserInfo(request, callback);
        }
    }

    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        boolean result = DEBUG || true;
        if (result) {
            ((PaymentHeatLocalMessageProcess) mLocal).requestPowerDetail(
                    context, callback);
        } else {
            ((PaymentHeatNetRequestMessageProcess) mNetRequest)
                    .requestPowerDetail(request, callback);
        }
    }
    
    
    public void requestList(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        boolean result = DEBUG || true;
        if (result) {
            ((PaymentHeatLocalMessageProcess) mLocal).requestQianfeiList(context,
                    callback);
        } else {
            ((PaymentHeatNetRequestMessageProcess) mNetRequest).requestQianfeiList(
                    request, callback);
        }
    }
}
