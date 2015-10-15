package com.smona.app.propertypayment.property.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;

public class PaymentPropertyPaySubmitBean extends PaymentSubmitBean {
    public String housingbantranscode;

    public PaymentPropertyPaySubmitBean() {
    }

    public PaymentPropertyPaySubmitBean(Parcel in) {
        super(in);
        housingbantranscode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(housingbantranscode);
    }

    public static final Parcelable.Creator<PaymentPropertyPaySubmitBean> CREATOR = new Creator<PaymentPropertyPaySubmitBean>() {
        @Override
        public PaymentPropertyPaySubmitBean[] newArray(int size) {
            return new PaymentPropertyPaySubmitBean[size];
        }

        @Override
        public PaymentPropertyPaySubmitBean createFromParcel(Parcel in) {
            return new PaymentPropertyPaySubmitBean(in);
        }
    };
}
