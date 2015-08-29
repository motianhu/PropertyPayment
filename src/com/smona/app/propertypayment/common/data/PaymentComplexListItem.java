package com.smona.app.propertypayment.common.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentComplexListItem extends PaymentItemInfo {
    public String companycode;
    public String companyname;
    public String paytime;
    public String money;
    public String objinfo;

    public PaymentComplexListItem() {

    }

    public PaymentComplexListItem(Parcel in) {
        companycode = in.readString();
        companyname = in.readString();
        paytime = in.readString();
        money = in.readString();
        objinfo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companycode);
        dest.writeString(companyname);
        dest.writeString(paytime);
        dest.writeString(money);
        dest.writeString(objinfo);
    }

    public static final Parcelable.Creator<PaymentComplexListItem> CREATOR = new Creator<PaymentComplexListItem>() {
        @Override
        public PaymentComplexListItem[] newArray(int size) {
            return new PaymentComplexListItem[size];
        }

        @Override
        public PaymentComplexListItem createFromParcel(Parcel in) {
            return new PaymentComplexListItem(in);
        }
    };

    public String toString() {
        return "PaymentListItem[companycode: " + companycode + ",companyname: "
                + companyname + ",paytime: " + paytime + ", monkey: " + money
                + ", objinfo: " + objinfo + "]";
    }
}
