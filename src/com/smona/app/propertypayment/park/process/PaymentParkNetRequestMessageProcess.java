package com.smona.app.propertypayment.park.process;

import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentParkNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentParkNetRequestMessageProcess.class
            .getSimpleName();

    public void requestParkChewei(IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_PARK_CHEWEI_INFO, callback);
    }

    public void requestParkDiscount(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_PARK_ZHEKOU, request, callback);
    }

    public void requestParkPlan(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_PARK_PLAN, request, callback);
    }
    
    public void requestParkDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentSimpleCodeConstants.MSG_PARK_PLAN_DETAIL, request, callback);
    }
}
