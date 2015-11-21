package com.smona.app.propertypayment.nontax.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentNonTaxNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentNonTaxNetRequestMessageProcess.class
            .getSimpleName();

    public void requestList(PaymentRequestInfo request, IQuestCallback callback) {
        requestCommon(PaymentNonTaxMessageProcessProxy.MSG_NONTAX_LIST,
                request, callback);
    }
}
