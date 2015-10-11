package com.smona.app.propertypayment.property.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentPropertyLocalMessageProcess extends
        PaymentLocalMessageProcess {
    public void requestPropertyFangchan(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "property_fangchan.txt"));
    }

    public void requestPropertyDiscount(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "property_zhekou.txt"));
    }

    public void requestPropertyPlan(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "property_plan.txt"));
    }
}
