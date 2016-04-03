package com.smona.app.propertypayment.common.data.submit;

import android.os.Parcel;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentSubmitBean extends PaymentItemInfo {
    public String paytype;
    public String bankname;
    public String bankcode;
    public String resulttradeno;

    public PaymentSubmitBean() {
        super();
    }

    public PaymentSubmitBean(Parcel in) {
        super(in);
        paytype = in.readString();
        bankname = in.readString();
        bankcode = in.readString();
        resulttradeno = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(paytype);
        dest.writeString(bankname);
        dest.writeString(bankcode);
        dest.writeString(resulttradeno);
    }

    @Override
    public String toString() {
        return super.toString() +"paytype: " + paytype + "; bankname: " + bankname + "; bankcode: " + bankcode + "; resulttradeno: " + resulttradeno;
    }

}
