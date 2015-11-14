package com.smona.app.propertypayment.common.data.submit;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentPowerSubmitBean extends PaymentSubmitBean {
    public String consno;
    public String trans_name;
    public String org_no;
    public String org_name;
    public String postradeno;
    public String exchg_atm;
    public String transfare;
    public String accountdate;

    public PaymentPowerSubmitBean() {
    }

    public PaymentPowerSubmitBean(Parcel in) {
        super(in);
        consno = in.readString();
        trans_name = in.readString();

        org_no = in.readString();
        org_name = in.readString();

        exchg_atm = in.readString();
        transfare = in.readString();

        postradeno = in.readString();
        accountdate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(consno);
        dest.writeString(trans_name);

        dest.writeString(org_no);
        dest.writeString(org_name);

        dest.writeString(exchg_atm);
        dest.writeString(transfare);

        dest.writeString(postradeno);
        dest.writeString(accountdate);
    }

    public static final Parcelable.Creator<PaymentPowerSubmitBean> CREATOR = new Creator<PaymentPowerSubmitBean>() {
        @Override
        public PaymentPowerSubmitBean[] newArray(int size) {
            return new PaymentPowerSubmitBean[size];
        }

        @Override
        public PaymentPowerSubmitBean createFromParcel(Parcel in) {
            return new PaymentPowerSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentPowerSubmitBean[" + super.toString() + ", consno:"
                + consno + ", trans_name: " + trans_name + ", org_no: "
                + org_no + ", org_name: " + org_name + ", postradeno: "
                + postradeno + ", exchg_atm:" + exchg_atm + ", transfare: "
                + transfare + ", accountdate: " + accountdate + "]";
    }
}
