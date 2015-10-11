package com.smona.app.propertypayment.property.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPropertyMessageProcessProxy extends
        PaymentMessageProcessProxy {

    private static final boolean DEBUG = true;

    public PaymentPropertyMessageProcessProxy() {
        mLocal = new PaymentPropertyLocalMessageProcess();
        mNetRequest = new PaymentPropertyNetRequestMessageProcess();
        mNetSubmit = new PaymentPropertyNetSubmitMessageProcess();
    }

    public void requestFangchan(Context context, IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPropertyLocalMessageProcess) mLocal)
                    .requestPropertyFangchan(context, callback);
        } else {
            ((PaymentPropertyNetRequestMessageProcess) mNetRequest)
                    .requestPropertyFangchan(callback);
        }
    }

    public void requestDiscount(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPropertyLocalMessageProcess) mLocal)
                    .requestPropertyDiscount(context, callback);
        } else {
            ((PaymentPropertyNetRequestMessageProcess) mNetRequest)
                    .requestPropertyDiscount(request, callback);
        }
    }
    
    public void requestPlan(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPropertyLocalMessageProcess) mLocal)
                    .requestPropertyPlan(context, callback);
        } else {
            ((PaymentPropertyNetRequestMessageProcess) mNetRequest)
                    .requestPropertyPlan(request, callback);
        }
    }
}
