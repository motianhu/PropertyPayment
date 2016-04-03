package com.smona.app.propertypayment.common.data.submit;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentCommonSubmitBean extends PaymentSubmitBean {
    public String companycode;  //
    public String companyname;  //

    public String communitycode; //小区编号
    public String pstartdate; //开始月份
    public String penddate; //结束月份
    public String needfare; //应缴金额
    public String did; //折扣编号
    public String storedfare;  //预存金额

    public PaymentCommonSubmitBean() {
    }

    public PaymentCommonSubmitBean(Parcel in) {
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

    public static final Parcelable.Creator<PaymentCommonSubmitBean> CREATOR = new Creator<PaymentCommonSubmitBean>() {
        @Override
        public PaymentCommonSubmitBean[] newArray(int size) {
            return new PaymentCommonSubmitBean[size];
        }

        @Override
        public PaymentCommonSubmitBean createFromParcel(Parcel in) {
            return new PaymentCommonSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentCommonSubmitBean[" + super.toString() + ", companycode:"
                + companycode + ", companyname: " + companyname
                + ", communitycode: " + communitycode + ", pstartdate: "
                + pstartdate + ", penddate: " + penddate + ", needfare: "
                + needfare + ", did: " + did + ", storedfare: " + storedfare
                + "]";
    }
}
