package com.smona.app.propertypayment.heat.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentHeatNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentHeatNetRequestMessageProcess.class
            .getSimpleName();

    public void requestCity(IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_POWER_CITY, callback);
    }

    public void requestCompany(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_POWER_COMPANY,
                request, callback);
    }

    public void requestUserInfo(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_POWER_USER_INFO,
                request, callback);
    }

    public void requestPowerDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_POWER_DETAIL,
                request, callback);
    }
    
    public void requestQianfeiList(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentHeatMessageProcessProxy.MSG_POWER_QIANFEI_DETAIL,
                request, callback);
    }
}
