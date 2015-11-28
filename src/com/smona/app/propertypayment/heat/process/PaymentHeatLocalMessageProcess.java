package com.smona.app.propertypayment.heat.process;

import com.smona.app.propertypayment.process.PaymentLocalMessageProcess;

import android.content.Context;

public class PaymentHeatLocalMessageProcess extends
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
        callback.onResult(true, geFileFromAssets(context, "heat_detail.txt"));
    }
    
    public void requestQianfeiList(Context context,
            final IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "heat_qianfei_details.txt"));
    }
}
