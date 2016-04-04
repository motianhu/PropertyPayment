package com.smona.app.propertypayment.common.simple;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jasonwang.informationhuimin.utils.MD5Utils;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleMessageProcessProxy;
import com.smona.app.propertypayment.common.ui.PaymentConfirmActivity;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.pay.PayConstants;
import com.tencent.mm.sdk.modelpay.PayReq;

import java.security.NoSuchAlgorithmException;

public class PaymentSimpleConfirmActivity extends PaymentConfirmActivity {
    private static final String TAG = PaymentSimpleConfirmActivity.class
            .getSimpleName();

    @SuppressLint("InflateParams")
    @Override
    protected void initBody() {
        View view = mRoot.findViewById(R.id.jiaofei_company);
        initText(view, R.id.name, R.string.payment_common_jiaofei_company);
        initText(view, R.id.value, ((PaymentSimpleSubmitBean) mParam).org_name);

        LogUtil.d(TAG, "mParam: " + mParam);

        view = mRoot.findViewById(R.id.jiaofei_money);
        initText(view, R.id.name, R.string.payment_common_yingjiao_money);
        initText(view, R.id.value, ((PaymentSimpleSubmitBean) mParam).exchg_atm
                + "元");

        ViewGroup zhifu = (ViewGroup) mRoot.findViewById(R.id.zhifu_channel);
        String[] channels = getResources()
                .getStringArray(R.array.zhifu_channel);
        int size = channels.length;
        View group;

        for (int i = 0; i < size; i++) {
            group = LayoutInflater.from(this).inflate(
                    R.layout.payment_common_zhifu_type, null);
            initTextImage(group, R.id.select_type_value, imageIds[i],
                    channels[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.topMargin = 3;
            group.setId(i);
            initView(group);
            zhifu.addView(group, params);
        }
    }

    public void initRequest() {
        mMessageProcess = new PaymentSimpleMessageProcessProxy();
        ((PaymentSimpleMessageProcessProxy) mMessageProcess).requestPaySubmit(
                PaymentSimpleCodeConstants.MSG_WATER_SUBMIT, this, mParam, this);
    }

    public void processWechat(String prepayid) {
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId			= PayConstants.WX_APP_ID;
        req.partnerId		= PayConstants.WX_PARTERN_ID;
        req.prepayId		= PayConstants.WX_PREPAY_ID;
        req.nonceStr		= PayConstants.WX_RANDOM_STR;
        req.timeStamp		= System.currentTimeMillis() + ""; //秒
        req.packageValue	= PayConstants.WX_PACKAGE;;
        req.sign		= PayConstants.WX_SIGN;//geneSign(req);
        req.extData		= "app data"; // optional

        showMessage("正常调起支付");
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        mPayManager.sendRequest(req);
    }

    private String geneSign(PayReq req) {
        String stringA = "appId=" + req.appId + "&partnerId=" + req.partnerId + "&prepayId=" + req.prepayId +  "&nonceStr=" + req.nonceStr
                + "&timeStamp=" + req.timeStamp + "&packageValue=" + req.packageValue;
        String signValueTemp = stringA + "&key=" + PayConstants.WX_APP_SECRET;
        String signValue = null;
        try {
            signValue = MD5Utils.getMD5(signValueTemp).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return signValue;
    }


}
