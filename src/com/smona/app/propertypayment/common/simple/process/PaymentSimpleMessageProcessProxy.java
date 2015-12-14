package com.smona.app.propertypayment.common.simple.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentSimpleMessageProcessProxy extends PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;

    public PaymentSimpleMessageProcessProxy() {
        mLocal = new PaymentSimpleLocalMessageProcess();
        mNetRequest = new PaymentSimpleNetRequestMessageProcess();
        mNetSubmit = new PaymentSimpleNetSubmitMessageProcess();
    }

    public void requestCity(String requestCode, Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentSimpleLocalMessageProcess) mLocal).requestCity(context,
                    callback);
        } else {
            ((PaymentSimpleNetRequestMessageProcess) mNetRequest)
                    .requestCity(requestCode, callback);
        }
    }

    public void requestCompany(String rquestCode, Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentSimpleLocalMessageProcess) mLocal).requestCompany(context,
                    callback);
        } else {
            ((PaymentSimpleNetRequestMessageProcess) mNetRequest)
                    .requestCompany(rquestCode, request, callback);
        }
    }

    public void requestUserInfo(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentSimpleLocalMessageProcess) mLocal).requestUserInfo(context,
                    callback);
        } else {
            ((PaymentSimpleNetRequestMessageProcess) mNetRequest)
                    .requestUserInfo(request, callback);
        }
    }

    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        boolean result = DEBUG || true;
        if (result) {
            ((PaymentSimpleLocalMessageProcess) mLocal).requestPowerDetail(
                    context, callback);
        } else {
            ((PaymentSimpleNetRequestMessageProcess) mNetRequest)
                    .requestPowerDetail(request, callback);
        }
    }
}
