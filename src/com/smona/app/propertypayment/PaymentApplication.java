package com.smona.app.propertypayment;

import java.lang.ref.WeakReference;

import com.smona.app.propertypayment.common.util.PaymentHelper;
import com.smona.app.propertypayment.db.table.PaymentProvider;
import com.smona.app.propertypayment.image.ImageLoaderManager;

import android.app.Application;

public class PaymentApplication extends Application {
    private WeakReference<PaymentProvider> mProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderManager.getInstance().initImageLoader(this);
        PaymentHelper.initParams(this);
    }

    public void setPropertyProvider(PaymentProvider provider) {
        mProvider = new WeakReference<PaymentProvider>(provider);
    }

    public PaymentProvider getPropertyProvider() {
        return mProvider.get();
    }
}
