package com.smona.app.propertypayment.pay;

import android.content.Context;

/**
 * Created by Moth on 16/4/4.
 */
public class PayManager {
    private IPay mPay;

    public PayManager(Context context){
        mPay = new WXPayImpl(context);
    }

    public int checkEnv(Context context) {
        return mPay.checkSupportPay(context);
    }

    public void sendRequest(Object obj) {
        mPay.sendRequest(obj);
    }

}
