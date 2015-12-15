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

    public void requestUserInfo(String rquestCode, PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(rquestCode, request, callback);
    }

    public void requestFeeDetail(String rquestCode, PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(rquestCode, request, callback);
    }
}
