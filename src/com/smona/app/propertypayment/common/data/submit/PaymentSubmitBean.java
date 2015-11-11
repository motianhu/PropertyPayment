package com.smona.app.propertypayment.common.data.submit;

import android.os.Parcel;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentSubmitBean extends PaymentItemInfo {
    public PaymentSubmitBean() {
        super();
    }

    public PaymentSubmitBean(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
