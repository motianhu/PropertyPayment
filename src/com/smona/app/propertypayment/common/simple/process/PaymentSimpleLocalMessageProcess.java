package com.smona.app.propertypayment.common.simple.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentSimpleLocalMessageProcess extends
        PaymentLocalMessageProcess {
    
    public void requestCity(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "power_chaxun_city.txt"));
    }
    
    public void requestCompany(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "power_chaxun_company.txt"));
    }
    
    public void requestUserInfo(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "power_chaxun_user.txt"));
    }
    
    public void requestPowerDetail(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "power_detail.txt"));
    }
}
