package com.smona.app.propertypayment.pay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * Created by Moth on 16/4/4.
 */
public class WXPayImpl implements IPay {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    public WXPayImpl(Context context){
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, PayConstants.WX_APP_ID, false);
        api.registerApp(PayConstants.WX_APP_ID);
    }

    @Override
    public int checkSupportPay(Context context) {
        boolean isInstallWechat = isWeixinAvilible(context);
        if(!isInstallWechat) {
            return PayConstants.PAY_NOT_INSTALL;
        }
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if(!isPaySupported) {
            return PayConstants.PAY_LOW_VERSION;
        }
        return PayConstants.PAY_ENV_OK;
    }

    @Override
    public void sendRequest(Object obj) {
        if(obj instanceof PayReq) {
            api.sendReq((PayReq)obj);
        }
    }

    private static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(PayConstants.PKG_WX)) {
                    return true;
                }
            }
        }
        return false;
    }
}
