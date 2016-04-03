package com.smona.app.propertypayment.common.simple.bean;

import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentSimpleSubmitBean extends PaymentSubmitBean {
    public String trans_name;  //公司名称
    public String org_name;  //公司名称
    public String exchg_atm; //滞纳金

    public String postradeno;  //系统流水号
    public String transfare;  //缴费金额
    public String accountdate;  //银行账务日期
    
    public PaymentSimpleSubmitBean() {
    }

    public PaymentSimpleSubmitBean(Parcel in) {
        super(in);
        trans_name = in.readString();
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
        dest.writeString(trans_name);
        dest.writeString(org_name);
        dest.writeString(exchg_atm);

        dest.writeString(transfare);
        dest.writeString(postradeno);
        dest.writeString(accountdate);
    }

    public static final Parcelable.Creator<PaymentSimpleSubmitBean> CREATOR = new Creator<PaymentSimpleSubmitBean>() {
        @Override
        public PaymentSimpleSubmitBean[] newArray(int size) {
            return new PaymentSimpleSubmitBean[size];
        }

        @Override
        public PaymentSimpleSubmitBean createFromParcel(Parcel in) {
            return new PaymentSimpleSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentSimpleSubmitBean[" + super.toString()  + ", trans_name: " + trans_name + ", org_name: " + org_name +", exchg_atm: " + exchg_atm +", postradeno: "
                + postradeno + ", transfare: "
                + transfare + ", accountdate: " + accountdate + "]";
    }
}
