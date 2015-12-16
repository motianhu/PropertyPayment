package com.smona.app.propertypayment.heat.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentHeatMessageProcessProxy extends PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;

    public static final String MSG_HEAT_DETAIL = "2100";
    public static final String MSG_HEAT_QIANFEI_DETAIL = "1900";

    public static final String MSG_HEAT_DETAIL_RESPONSE = "2110";
    public static final String MSG_HEAT_QIANFEI_DETAIL_RESPONSE = "1910";

    public PaymentHeatMessageProcessProxy() {
        mLocal = new PaymentHeatLocalMessageProcess();
        mNetRequest = new PaymentHeatNetRequestMessageProcess();
        mNetSubmit = new PaymentHeatNetSubmitMessageProcess();
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
