package com.smona.app.propertypayment.common.ui;

import android.app.AlertDialog;
import android.content.Context;

public class PaymentSelectedDialog extends AlertDialog {

    protected PaymentSelectedDialog(Context context, boolean cancelable,
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
