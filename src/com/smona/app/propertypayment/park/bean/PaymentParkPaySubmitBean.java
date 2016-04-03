package com.smona.app.propertypayment.park.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.smona.app.propertypayment.common.data.submit.PaymentCommonSubmitBean;

public class PaymentParkPaySubmitBean extends PaymentCommonSubmitBean {
    public String parknum;

    public PaymentParkPaySubmitBean() {
    }

    public PaymentParkPaySubmitBean(Parcel in) {
        super(in);
        parknum = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(parknum);
    }

    public static final Parcelable.Creator<PaymentParkPaySubmitBean> CREATOR = new Creator<PaymentParkPaySubmitBean>() {
        @Override
        public PaymentParkPaySubmitBean[] newArray(int size) {
            return new PaymentParkPaySubmitBean[size];
        }

        @Override
        public PaymentParkPaySubmitBean createFromParcel(Parcel in) {
            return new PaymentParkPaySubmitBean(in);
        }
    };

    public String toString(){
        return super.toString() + "; PaymentParkPaySubmitBean: parknum=" + parknum;
    }
}
