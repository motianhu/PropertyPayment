package com.smona.app.propertypayment.power.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentPowerLocalMessageProcess extends
        PaymentLocalMessageProcess {
    public void requestUserInfo(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "power_chaxun.txt"));
    }
    
    public void requestPowerDetail(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "property_detail.txt"));
    }
}
