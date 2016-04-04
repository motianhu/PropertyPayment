package com.smona.app.propertypayment.water.bean;

import android.os.Parcel;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;

/**
 * Created by Moth on 16/4/4.
 */
public class PaymentWaterSubmitBean extends PaymentSimpleSubmitBean {
    public String consno;  //用户户号
    public String org_no;  //供水公司代码

    public PaymentWaterSubmitBean() {
    }

    public PaymentWaterSubmitBean(Parcel in) {
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

    public static final Creator<PaymentWaterSubmitBean> CREATOR = new Creator<PaymentWaterSubmitBean>() {
        @Override
        public PaymentWaterSubmitBean[] newArray(int size) {
            return new PaymentWaterSubmitBean[size];
        }

        @Override
        public PaymentWaterSubmitBean createFromParcel(Parcel in) {
            return new PaymentWaterSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentWaterSubmitBean[" + super.toString()  + ", consno: "
                + consno + ", org_no: "
                + org_no + "]";
    }
}
