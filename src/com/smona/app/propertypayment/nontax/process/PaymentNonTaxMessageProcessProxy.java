package com.smona.app.propertypayment.nontax.process;

import android.content.Context;

import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentNonTaxMessageProcessProxy extends
        PaymentMessageProcessProxy {

    private static final boolean DEBUG = true;

    public static final String MSG_NONTAX_LIST = "0700";
    
    public static final String MSG_NONTAX_LISTRESPONSE = "0710";

    public PaymentNonTaxMessageProcessProxy() {
        mLocal = new PaymentNonTaxLocalMessageProcess();
        mNetRequest = new PaymentNonTaxNetRequestMessageProcess();
        mNetSubmit = new PaymentNonTaxNetSubmitMessageProcess();
    }

    public void requestList(Context context, PaymentRequestInfo request,
            IQuestCallback callback) {
        if (DEBUG) {
            ((PaymentNonTaxLocalMessageProcess) mLocal).requestList(context,
                    callback);
        } else {
            ((PaymentNonTaxNetRequestMessageProcess) mNetRequest).requestList(
                    request, callback);
        }
    }
}
