package com.smona.app.propertypayment.common.data.submit;

import android.os.Parcel;
import android.os.Parcelable;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentSubmitBean extends PaymentItemInfo {
    public String companycode;
    public String companyname;

    public String communitycode;
    public String pstartdate;
    public String penddate;
    public String needfare;
    public String did;
    public String storedfare;

    public PaymentSubmitBean() {
    }

    public PaymentSubmitBean(Parcel in) {
        super(in);
        companycode = in.readString();
        companyname = in.readString();

        communitycode = in.readString();
        pstartdate = in.readString();
        penddate = in.readString();
        needfare = in.readString();
        did = in.readString();
        storedfare = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(companycode);
        dest.writeString(companyname);

        dest.writeString(communitycode);
        dest.writeString(pstartdate);
        dest.writeString(penddate);
        dest.writeString(needfare);
        dest.writeString(did);
        dest.writeString(storedfare);
    }

    public static final Parcelable.Creator<PaymentSubmitBean> CREATOR = new Creator<PaymentSubmitBean>() {
        @Override
        public PaymentSubmitBean[] newArray(int size) {
            return new PaymentSubmitBean[size];
        }

        @Override
        public PaymentSubmitBean createFromParcel(Parcel in) {
            return new PaymentSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentSubmitBean[" + super.toString() + ", companycode:"
                + companycode + ", companyname: " + companyname
                + ", communitycode: " + communitycode + ", pstartdate: "
                + pstartdate + ", penddate: " + penddate + ", needfare: "
                + needfare + ", did: " + did + ", storedfare: " + storedfare
                + "]";
    }
}
