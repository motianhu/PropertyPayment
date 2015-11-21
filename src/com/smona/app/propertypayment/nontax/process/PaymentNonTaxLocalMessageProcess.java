package com.smona.app.propertypayment.nontax.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentNonTaxLocalMessageProcess extends
        PaymentLocalMessageProcess {
    public void requestList(Context context, final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "nontax.txt"));
    }

    public void requestDetail(Context context, final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "nontax_detail.txt"));
    }
}
