package com.smona.app.propertypayment.park.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentParkLocalMessageProcess extends
        PaymentLocalMessageProcess {
    public void requestParkChewei(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "park_chewei.txt"));
    }

    public void requestParkDiscount(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "park_zhekou.txt"));
    }

    public void requestParkPlan(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "park_plan.txt"));
    }
    
    public void requestParkDetail(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "park_detail.txt"));
    }
}
