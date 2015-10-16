package com.smona.app.propertypayment.power.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPowerMessageProcessProxy extends
        PaymentMessageProcessProxy {

    private static final boolean DEBUG = true;
    

    public static final String MSG_POWER_USER_INFO = "1200";
    public static final String MSG_POWER_DETAIL = "1700";
    
    public static final String MSG_POWER_USER_INFO_RESPONSE = "1210";
    public static final String MSG_POWER_DETAIL_RESPONSE = "1710";

    public PaymentPowerMessageProcessProxy() {
        mLocal = new PaymentPowerLocalMessageProcess();
        mNetRequest = new PaymentPowerNetRequestMessageProcess();
        mNetSubmit = new PaymentPowerNetSubmitMessageProcess();
    }

    public void requestUserInfo(Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPowerLocalMessageProcess) mLocal)
                    .requestUserInfo(context, callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestUserInfo(callback);
        }
    }
    
    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPowerLocalMessageProcess) mLocal)
                    .requestPowerDetail(context, callback);
        } else {
            ((PaymentPowerNetRequestMessageProcess) mNetRequest)
                    .requestPowerDetail(request, callback);
        }
    }
}
