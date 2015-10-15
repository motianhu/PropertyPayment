package com.smona.app.propertypayment.park.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentParkNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    private static final String TAG = "PaymentPARKNetRequestMessageProcess";

    private void requestCommon(final String MSG_CODE,
            final IQuestCallback callback) {
        new Thread() {
            public void run() {
                JSONAuthenticationMessage message = new JSONAuthenticationMessage();
                message.setIccode(MSG_CODE);
                message.setSessionid(ConfigsInfo.sesssionId);
                message.setLoginname(ConfigsInfo.username);
                String msg = new Gson().toJson(message);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestCommon result " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    callback.onResult(false, null);
                } else {
                    callback.onResult(true, result);
                }
            }
        }.start();
    }

    private void requestCommon(final String MSG_CODE,
            final PaymentRequestInfo request, final IQuestCallback callback) {
        new Thread() {
            public void run() {
                request.iccode = MSG_CODE;
                request.sessionid = ConfigsInfo.sesssionId;
                request.loginname = ConfigsInfo.username;

                String msg = new Gson().toJson(request);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestCommon request result:  " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    callback.onResult(false, null);
                } else {
                    callback.onResult(true, result);
                }
            }
        }.start();
    }

    public void requestParkChewei(IQuestCallback callback) {
        requestCommon(PaymentParkMessageProcessProxy.MSG_PARK_CHEWEI, callback);
    }

    public void requestParkDiscount(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentParkMessageProcessProxy.MSG_PARK_DISCOUNT, request, callback);
    }

    public void requestParkPlan(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentParkMessageProcessProxy.MSG_PARK_PLAN, request, callback);
    }
    
    public void requestParkDetail(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(PaymentParkMessageProcessProxy.MSG_PARK_DETAIL, request, callback);
    }
}
