package com.smona.app.propertypayment.property.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;
import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentPropertyNetRequestMessageProcess extends
        PaymentNetRequestMessageProcess {
    private static final String TAG = "PaymentPropertyNetRequestMessageProcess";

    private static final String MSG_PROPERTY_FANGCHAN = "0200";
    private static final String MSG_PROPERTY_DISCOUNT = "0300";
    private static final String MSG_PROPERTY_PLAN = "0400";

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

    public void requestPropertyFangchan(IQuestCallback callback) {
        requestCommon(MSG_PROPERTY_FANGCHAN, callback);
    }

    public void requestPropertyDiscount(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(MSG_PROPERTY_DISCOUNT, request, callback);
    }

    public void requestPropertyPlan(PaymentRequestInfo request,
            IQuestCallback callback) {
        requestCommon(MSG_PROPERTY_PLAN, request, callback);
    }
}
