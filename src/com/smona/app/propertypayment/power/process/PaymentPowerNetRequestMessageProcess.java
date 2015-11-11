package com.smona.app.propertypayment.power.process;

import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPowerNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentPowerNetRequestMessageProcess.class
            .getSimpleName();

    public void requestCity(IQuestCallback callback) {
        requestCommon(PaymentPowerMessageProcessProxy.MSG_POWER_CITY, callback);
    }

    public void requestCompany(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPowerMessageProcessProxy.MSG_POWER_COMPANY,
                request, callback);
    }

    public void requestUserInfo(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPowerMessageProcessProxy.MSG_POWER_USER_INFO,
                request, callback);
    }

    public void requestPowerDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentPowerMessageProcessProxy.MSG_POWER_DETAIL,
                request, callback);
    }
}
