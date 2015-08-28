package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentSimpleDetailInfo extends PaymentItemInfo {
    public String username;
    public String housecode;
    public String companycode;
    public String companyname;
    public String groudcode;
    public String groudname;
    public double money;

    public PaymentSimpleDetailInfo() {

    }

    public PaymentSimpleDetailInfo(Parcel in) {
        username = in.readString();
        housecode = in.readString();
        companycode = in.readString();
        companyname = in.readString();
        groudcode = in.readString();
        groudname = in.readString();
        money = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(housecode);
        dest.writeString(companycode);
        dest.writeString(companyname);
        dest.writeString(groudcode);
        dest.writeString(groudname);
        dest.writeDouble(money);
    }

    public static final Parcelable.Creator<PaymentSimpleDetailInfo> CREATOR = new Creator<PaymentSimpleDetailInfo>() {
        @Override
        public PaymentSimpleDetailInfo[] newArray(int size) {
            return new PaymentSimpleDetailInfo[size];
        }

        @Override
        public PaymentSimpleDetailInfo createFromParcel(Parcel in) {
            return new PaymentSimpleDetailInfo(in);
        }
    };

    public String toString() {
        return "PaymentSimpleDetailInfo[username: " + username + ",housecode: "
                + housecode + ",companycode: " + companycode + ",companyname: "
                + companyname + ",groudcode: " + groudcode + ", groudname: "
                + groudname + ", money: " + money + "]";
    }
}
