package com.smona.app.propertypayment.heat.bean;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleSubmitBean;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Moth on 16/4/4.
 */
public class PaymentHeatSubmitBean extends PaymentSimpleSubmitBean {
    public String heatsno;  //用户户号
    public String orgheat_no;  //热力公司代码

    public PaymentHeatSubmitBean() {
    }

    public PaymentHeatSubmitBean(Parcel in) {
        super(in);
        heatsno = in.readString();
        orgheat_no = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(heatsno);
        dest.writeString(orgheat_no);
    }

    public static final Parcelable.Creator<PaymentHeatSubmitBean> CREATOR = new Creator<PaymentHeatSubmitBean>() {
        @Override
        public PaymentHeatSubmitBean[] newArray(int size) {
            return new PaymentHeatSubmitBean[size];
        }

        @Override
        public PaymentHeatSubmitBean createFromParcel(Parcel in) {
            return new PaymentHeatSubmitBean(in);
        }
    };

    public String toString() {
        return "PaymentHeatSubmitBean[" + super.toString()  + ", heatsno: "
                + heatsno + ", orgheat_no: "
                + orgheat_no + "]";
    }
}
