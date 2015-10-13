package com.smona.app.propertypayment.park.process;

import com.smona.app.propertypayment.process.PaymentNetSubmitMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

import android.content.Context;

public class PaymentParkSubmitMessageProcess extends
        PaymentNetSubmitMessageProcess {
    private static final String MSG_WUYEBAOXIU_SUBMIT = "3300";
    private static final String MSG_WUYEBAOXIU_PINGJIA_SUBMIT = "3600";

    public void submitWuyebaoxiudan(Context context,
            PaymentRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYEBAOXIU_SUBMIT, request, callback);
    }

    public void submitWuyebaoxiudanPingjia(Context context,
            PaymentRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYEBAOXIU_PINGJIA_SUBMIT, request, callback);
    }
}
