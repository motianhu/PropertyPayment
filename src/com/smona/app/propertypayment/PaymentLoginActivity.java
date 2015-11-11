package com.smona.app.propertypayment;

import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class PaymentLoginActivity extends PaymentBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_login);
        initViews();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.payment_payment);
        initView(R.id.back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initBody() {
        initView(R.id.login);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }

        switch (id) {
        case R.id.login:
            login();
            break;
        }
    }

    protected void saveData(String content) {
        showMessage("登录成功");
        gotoSubActivity(PayHomeActivity.class);
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        showMessage("登录失败");
        hideCustomProgressDialog();
    }

    private void login() {
        TextView ip = (TextView) findViewById(R.id.ipaddress);
        TextView port = (TextView) findViewById(R.id.port);
        TextView user = (TextView) findViewById(R.id.user_name);
        TextView pass = (TextView) findViewById(R.id.password);
        if (TextUtils.isEmpty(user.getText())
                || TextUtils.isEmpty(pass.getText())) {
            return;
        }

        // login
        PaymentNetLoginMessageProcess process = new PaymentNetLoginMessageProcess();
        ((PaymentNetLoginMessageProcess) process).login(this, ip.getText()
                .toString(), port.getText().toString(), user.getText()
                .toString(), pass.getText().toString());
        showCustomProgrssDialog();
    }
}
