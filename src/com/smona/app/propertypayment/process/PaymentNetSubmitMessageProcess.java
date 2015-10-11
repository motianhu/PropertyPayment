package com.smona.app.propertypayment.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertypayment.common.util.LogUtil;

public class PaymentNetSubmitMessageProcess extends PaymentMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    protected void submitRequest(final String MSG_CODE,
            final PaymentRequestInfo request, final IQuestCallback callback) {
        new Thread() {
            public void run() {
                request.iccode = MSG_CODE;
                request.loginname = ConfigsInfo.username;
                request.sessionid = ConfigsInfo.sesssionId;

                String msg = new Gson().toJson(request);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "submitRequest result " + result + ", msg: "
                        + msg + ", callback: " + callback);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    if (callback != null) {
                        callback.onResult(false, null);
                    }
                } else {
                    if (callback != null) {
                        callback.onResult(true, result);
                    }
                }
            }
        }.start();
    }
}
