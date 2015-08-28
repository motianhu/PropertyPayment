package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentListItem extends PaymentItemInfo {
    public String companycode;
    public String companyname;
    public String groupcode;
    public String groupname;
    public String paytime;
    public String money;
    public String housecode;

    public PaymentListItem() {

    }

    public PaymentListItem(Parcel in) {
        companycode = in.readString();
        companyname = in.readString();
        groupcode = in.readString();
        groupname = in.readString();
        paytime = in.readString();
        money = in.readString();
        housecode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companycode);
        dest.writeString(companyname);
        dest.writeString(groupcode);
        dest.writeString(groupname);
        dest.writeString(paytime);
        dest.writeString(money);
        dest.writeString(housecode);
    }

    public static final Parcelable.Creator<PaymentListItem> CREATOR = new Creator<PaymentListItem>() {
        @Override
        public PaymentListItem[] newArray(int size) {
            return new PaymentListItem[size];
        }

        @Override
        public PaymentListItem createFromParcel(Parcel in) {
            return new PaymentListItem(in);
        }
    };

    public String toString() {
        return "PaymentListItem[companycode: " + companycode + ",companyname: "
                + companyname + ",groupcode: " + groupcode + ",groupname: "
                + groupname + ",paytime: " + paytime + ", monkey: " + money
                + ", housecode: " + housecode + "]";
    }
}
