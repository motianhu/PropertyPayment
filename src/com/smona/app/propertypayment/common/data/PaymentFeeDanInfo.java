package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentFeeDanInfo extends PaymentItemInfo {
    public String companycode;
    public String companyname;
    public double money;

    public PaymentFeeDanInfo() {

    }

    public PaymentFeeDanInfo(Parcel in) {
        companycode = in.readString();
        companyname = in.readString();
        money = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companycode);
        dest.writeString(companyname);
        dest.writeDouble(money);
    }

    public static final Parcelable.Creator<PaymentFeeDanInfo> CREATOR = new Creator<PaymentFeeDanInfo>() {
        @Override
        public PaymentFeeDanInfo[] newArray(int size) {
            return new PaymentFeeDanInfo[size];
        }

        @Override
        public PaymentFeeDanInfo createFromParcel(Parcel in) {
            return new PaymentFeeDanInfo(in);
        }
    };

    public String toString() {
        return "PaymentFeeDanInfo[companycode: " + companycode
                + ",companyname: " + companyname + ", money: " + money;
    }
}
