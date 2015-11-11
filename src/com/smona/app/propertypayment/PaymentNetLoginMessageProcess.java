package com.smona.app.propertypayment;

import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.process.PaymentNetRequestMessageProcess;

public class PaymentNetLoginMessageProcess extends
        PaymentNetRequestMessageProcess {
    public static String TAG = PaymentNetLoginMessageProcess.class
            .getSimpleName();;

    public void login(final IQuestCallback callback, String ip, String port,
            final String username, final String passwd) {

        PaymentConstants.HTTP_IP_PORT = PaymentConstants.HTTP + ip + ":" + port;

        ConfigsInfo.FWURL = PaymentConstants.HTTP_IP_PORT
                + PaymentConstants.URL_SUFFIX;
        new Thread() {
            public void run() {
                boolean flag = new DoHttp().authentication();
                LogUtil.d(TAG, "login flag: " + flag + ", username: "
                        + username + ", passwd: " + passwd + ", url: " + ConfigsInfo.FWURL );
                if (flag) {
                    String result = new DoHttp().doLogin(username, passwd);
                    LogUtil.d(TAG, "login result: " + result
                            + ", ConfigsInfo: " + ConfigsInfo.sesssionId);
                    callback.onResult("1".equals(result), "");
                    return;
                }
                callback.onResult(false, "failed");
            }
        }.start();

    }
}
