package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentSimpleListItem extends PaymentItemInfo {
    public String companycode;
    public String companyname;
    public String groupcode;
    public String groupname;
    public String paytime;
    public String money;
    public String housecode;

    public PaymentSimpleListItem() {

    }

    public PaymentSimpleListItem(Parcel in) {
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

    public static final Parcelable.Creator<PaymentSimpleListItem> CREATOR = new Creator<PaymentSimpleListItem>() {
        @Override
        public PaymentSimpleListItem[] newArray(int size) {
            return new PaymentSimpleListItem[size];
        }

        @Override
        public PaymentSimpleListItem createFromParcel(Parcel in) {
            return new PaymentSimpleListItem(in);
        }
    };

    public String toString() {
        return "PaymentListItem[companycode: " + companycode + ",companyname: "
                + companyname + ",groupcode: " + groupcode + ",groupname: "
                + groupname + ",paytime: " + paytime + ", monkey: " + money
                + ", housecode: " + housecode + "]";
    }
}
