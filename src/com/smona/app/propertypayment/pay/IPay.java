package com.smona.app.propertypayment.pay;

import android.content.Context;

/**
 * Created by Moth on 16/4/4.
 */
public interface IPay {
    int checkSupportPay(Context context);
    void sendRequest(Object obj);
}
