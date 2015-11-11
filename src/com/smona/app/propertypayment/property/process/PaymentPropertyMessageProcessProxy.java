package com.smona.app.propertypayment.property.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPropertyMessageProcessProxy extends
        PaymentMessageProcessProxy {

    private static final boolean DEBUG = false;
    
    public static final String MSG_PROPERTY_FANGCHAN = "0200";
    public static final String MSG_PROPERTY_DISCOUNT = "0300";
    public static final String MSG_PROPERTY_PLAN = "0400";
    public static final String MSG_PROPERTY_DETAIL = "1500";
    
    public static final String MSG_PROPERTY_FANGCHAN_RESPONSE = "0210";
    public static final String MSG_PROPERTY_DISCOUNT_RESPONSE = "0310";
    public static final String MSG_PROPERTY_PLAN_RESPONSE = "0410";
    public static final String MSG_PROPERTY_DETAIL_RESPONSE = "1510";

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
    
    public void requestDetail(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentPropertyLocalMessageProcess) mLocal)
                    .requestPropertyDetail(context, callback);
        } else {
            ((PaymentPropertyNetRequestMessageProcess) mNetRequest)
                    .requestPropertyDetail(request, callback);
        }
    }
}
