package com.smona.app.propertypayment.common.ui;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.process.PaymentMessageProcessProxy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;

@SuppressLint("Registered")
public class PaymentDialogActivity extends Activity {

    private static final int DIALOG_SHOW = 0;
    private static final int DIALOG_HIDE = 1;

    protected PaymentMessageProcessProxy mMessageProcess;
    protected PaymentLoadingDialog mDialog;

    @SuppressLint("HandlerLeak")
    private Handler mDialogHandler = new Handler() {
        public void handleMessage(Message msg) {
            int what = msg.what;
            LogUtil.d("", "what " + what);
            switch (what) {
            case DIALOG_SHOW:
                showDialogForMessage((String) msg.obj);
                break;
            case DIALOG_HIDE:
                hideDialogForMessage();
                break;
            }
        }
    };

    protected void showCustomProgrssDialog() {
        showCustomProgrssDialog("");
    }

    protected void showCustomProgrssDialog(String msg) {
        mDialogHandler.sendEmptyMessage(DIALOG_SHOW);
    }

    public void hideCustomProgressDialog() {
        mDialogHandler.sendEmptyMessage(DIALOG_HIDE);
    }

    private void showDialogForMessage(String msg) {
        hideDialogForMessage();
        mDialog = new PaymentLoadingDialog(this,
                R.style.Payment_Progress_Dialog);
        mDialog.setContentView(R.layout.payment_common_loading);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        mDialog.startAnim();
        mDialog.setMessage(msg);
        mDialog.show();
        mDialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                hideDialogForMessage();
            }
        });
    }

    private void hideDialogForMessage() {
        if (null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
