package com.smona.app.propertypayment.park.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentParkMessageProcessProxy extends
        PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;

    public PaymentParkMessageProcessProxy() {
        mLocal = new PaymentParkLocalMessageProcess();
        mNetRequest = new PaymentParkNetRequestMessageProcess();
        mNetSubmit = new PaymentParkSubmitMessageProcess();
    }

    public void requestChewei(Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentParkLocalMessageProcess) mLocal)
                    .requestParkChewei(context, callback);
        } else {
            ((PaymentParkNetRequestMessageProcess) mNetRequest)
                    .requestParkChewei(callback);
        }
    }

    public void requestDiscount(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentParkLocalMessageProcess) mLocal)
                    .requestParkDiscount(context, callback);
        } else {
            ((PaymentParkNetRequestMessageProcess) mNetRequest)
                    .requestParkDiscount(request, callback);
        }
    }
    
    public void requestPlan(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentParkLocalMessageProcess) mLocal)
                    .requestParkPlan(context, callback);
        } else {
            ((PaymentParkNetRequestMessageProcess) mNetRequest)
                    .requestParkPlan(request, callback);
        }
    }
    
    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentParkLocalMessageProcess) mLocal)
                    .requestParkDetail(context, callback);
        } else {
            ((PaymentParkNetRequestMessageProcess) mNetRequest)
                    .requestParkDetail(request, callback);
        }
    }
}
