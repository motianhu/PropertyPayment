package com.smona.app.propertypayment.common.ui;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleCodeConstants;
import com.smona.app.propertypayment.common.simple.process.PaymentSimpleMessageProcessProxy;
import com.smona.app.propertypayment.common.util.JsonUtils;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;
import com.smona.app.propertypayment.pay.PayConstants;
import com.smona.app.propertypayment.pay.PayManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class PaymentConfirmActivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentConfirmActivity";

    protected PaymentSubmitBean mParam;
    protected int mSource;
    protected int[] imageIds;

    protected PayManager mPayManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirm);
        aquireData();
        initViews();
    }

    private void aquireData() {
        imageIds = new int[] { R.drawable.payment_zhifubao,
                R.drawable.payment_weixin, R.drawable.payment_yanlian };
        mParam = (PaymentSubmitBean) getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
        mSource = getIntent().getIntExtra(PaymentConstants.DATA_SOURCE, -1);
        LogUtil.d(TAG, "acquireItemInfo mSource: " + mSource + ", mParam: "
                + mParam);

        mPayManager = new PayManager(this);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_zhifu);
        initView(R.id.back);
    }

    protected void initTextImage(View parent, int childId, int imgId, String str) {
        TextView text = (TextView) parent.findViewById(childId);
        text.setCompoundDrawablesWithIntrinsicBounds(getResources()
                .getDrawable(imgId), null, null, null);
        text.setText(str);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case 0:
            gotoAliPay();
            break;
        case 1:
            gotoWechatPay();
            break;
        case 2:
            gotoYanlian();
            break;
        }
    }

    private void gotoAliPay() {
        this.showMessage("支付宝!");
    }

    private void gotoWechatPay() {
        int envFlag = mPayManager.checkEnv(this);
        if(PayConstants.PAY_NOT_INSTALL == envFlag){
            this.showMessage("请安装微信!");
            return;
        }
        if(PayConstants.PAY_LOW_VERSION == envFlag){
            this.showMessage("微信版本太低，不支持支付，请升级微信!");
            return;
        }
        this.showMessage("微信支付!");
        loadData();
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        showCustomProgrssDialog();
        initRequest();
    }

    abstract public void initRequest();

    protected void saveData(String content) {
        Type type = new TypeToken<PaymentItemInfo>() {
        }.getType();
        PaymentItemInfo bean = JsonUtils.parseJson(content, type);
        if (PaymentSimpleCodeConstants.MSG_WATER_SUBMIT_RESPONSE
                .equals(bean.iccode)) {
            if (isRequestOk(bean)) {

            } else {

            }
        } else {

        }
        processWechat("");
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void gotoYanlian() {
        this.showMessage("银联支付!");
    }

    abstract protected void processWechat(String prepayid);
}
