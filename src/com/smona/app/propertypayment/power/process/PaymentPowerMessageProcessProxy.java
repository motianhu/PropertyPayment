package com.smona.app.propertypayment.power.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPowerMessageProcessProxy extends PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;

    public static final String MSG_POWER_USER_INFO = "1200";
    public static final String MSG_POWER_SUBMIT = "1300";
    public static final String MSG_POWER_CITY = "1400";
    public static final String MSG_POWER_COMPANY = "1800";
    public static final String MSG_POWER_DETAIL = "1700";

    public static final String MSG_POWER_USER_INFO_RESPONSE = "1210";
    public static final String MSG_POWER_SUBMIT_RESPONSE = "1310";
    public static final String MSG_POWER_CITY_RESPONSE = "1410";
    public static final String MSG_POWER_COMPANY_RESPONSE = "1810";
    public static final String MSG_POWER_DETAIL_RESPONSE = "1710";

    public PaymentPowerMessageProcessProxy() {
        mLocal = new PaymentPowerLocalMessageProcess();
        mNetRequest = new PaymentPowerNetRequestMessageProcess();
        mNetSubmit = new PaymentPowerNetSubmitMessageProcess();
    }

    public void requestCity(Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPowerLocalMessageProcess) mLocal).requestCity(context,
                    callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestCity(callback);
        }
    }

    public void requestCompany(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPowerLocalMessageProcess) mLocal).requestCompany(context,
                    callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestCompany(request, callback);
        }
    }

    public void requestUserInfo(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPowerLocalMessageProcess) mLocal).requestUserInfo(context,
                    callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestUserInfo(request, callback);
        }
    }

    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG || true) {
            ((PaymentPowerLocalMessageProcess) mLocal).requestPowerDetail(
                    context, callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestPowerDetail(request, callback);
        }
    }
}
