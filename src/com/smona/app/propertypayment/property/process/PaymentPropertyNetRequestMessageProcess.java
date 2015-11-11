package com.smona.app.propertypayment.property.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPropertyNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentPropertyNetRequestMessageProcess.class
            .getSimpleName();

    public void requestPropertyFangchan(IQuestCallback callback) {
        requestCommon(PaymentPropertyMessageProcessProxy.MSG_PROPERTY_FANGCHAN,
                callback);
    }

    public void requestPropertyDiscount(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPropertyMessageProcessProxy.MSG_PROPERTY_DISCOUNT,
                request, callback);
    }

    public void requestPropertyPlan(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPropertyMessageProcessProxy.MSG_PROPERTY_PLAN,
                request, callback);
    }

    public void requestPropertyDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPropertyMessageProcessProxy.MSG_PROPERTY_DETAIL,
                request, callback);
    }
}
