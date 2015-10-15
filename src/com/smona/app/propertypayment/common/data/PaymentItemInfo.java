package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentItemInfo implements Parcelable {
    public String iccode;
    public String loginname;
    public String answercode;
    public String sessionid;

    public PaymentItemInfo() {
    }

    public PaymentItemInfo(Parcel in) {
        iccode = in.readString();
        loginname = in.readString();
        answercode = in.readString();
        sessionid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iccode);
        dest.writeString(loginname);
        dest.writeString(answercode);
        dest.writeString(sessionid);
    }

    public static final Parcelable.Creator<PaymentItemInfo> CREATOR = new Creator<PaymentItemInfo>() {
        @Override
        public PaymentItemInfo[] newArray(int size) {
            return new PaymentItemInfo[size];
        }

        @Override
        public PaymentItemInfo createFromParcel(Parcel in) {
            return new PaymentItemInfo(in);
        }
    };

    public String toString() {
        return "iccode: " + iccode + ", sessionid: " + sessionid
                + ", answercode: " + answercode + ", loginname: " + loginname;
    }
}
