package com.smona.app.propertypayment.gas.bean;

import android.os.Parcel;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;

/**
 * Created by Moth on 16/4/4.
 */
public class PaymentGasSubmitBean extends PaymentSimpleSubmitBean {
    public String gasno;  //用户户号
    public String orggas_no;  //燃气公司代码

    public PaymentGasSubmitBean() {
    }

    public PaymentGasSubmitBean(Parcel in) {
        super(in);
        gasno = in.readString();
        orggas_no = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(gasno);
        dest.writeString(orggas_no);
    }

    public static final Creator<PaymentGasSubmitBean> CREATOR = new Creator<PaymentGasSubmitBean>() {
        @Override
        public PaymentGasSubmitBean[] newArray(int size) {
            return new PaymentGasSubmitBean[size];
        }

        @Override
        public PaymentGasSubmitBean createFromParcel(Parcel in) {
            return new PaymentGasSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentGasSubmitBean[" + super.toString()  + ", gasno: "
                + gasno + ", orggas_no: "
                + orggas_no + "]";
    }
}
