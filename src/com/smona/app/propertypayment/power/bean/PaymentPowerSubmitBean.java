package com.smona.app.propertypayment.power.bean;

import android.os.Parcel;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;

/**
 * Created by Moth on 16/4/4.
 */
public class PaymentPowerSubmitBean extends PaymentSimpleSubmitBean {
    public String consno;  //用户户号
    public String org_no;  //供电公司代码

    public PaymentPowerSubmitBean() {
    }

    public PaymentPowerSubmitBean(Parcel in) {
        super(in);
        consno = in.readString();
        org_no = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(consno);
        dest.writeString(org_no);
    }

    public static final Creator<PaymentPowerSubmitBean> CREATOR = new Creator<PaymentPowerSubmitBean>() {
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
        return "PaymentPowerSubmitBean[" + super.toString()  + ", consno: "
                + consno + ", org_no: "
                + org_no + "]";
    }
}
