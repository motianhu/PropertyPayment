package com.smona.app.propertypayment.common.simple.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentSimpleNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentSimpleNetRequestMessageProcess.class
            .getSimpleName();

    public void requestCity(String requestCode, IQuestCallback callback) {
        requestCommon(requestCode, callback);
    }

    public void requestCompany(String requestCode, PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(requestCode, request, callback);
    }

    public void requestUserInfo(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_POWER_USER_INFO, request,
                callback);
    }

    public void requestPowerDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_POWER_DETAIL, request,
                callback);
    }
}
