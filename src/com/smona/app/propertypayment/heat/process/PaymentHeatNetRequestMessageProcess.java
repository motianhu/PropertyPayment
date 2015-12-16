package com.smona.app.propertypayment.heat.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentHeatNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentHeatNetRequestMessageProcess.class
            .getSimpleName();

    public void requestPowerDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_HEAT_DETAIL,
                request, callback);
    }
    
    public void requestQianfeiList(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_HEAT_QIANFEI_DETAIL,
                request, callback);
    }
}
