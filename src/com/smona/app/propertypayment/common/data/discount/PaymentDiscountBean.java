package com.smona.app.propertypayment.common.data.discount;

import android.content.Context;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentDiscountBean extends PaymentItemInfo {
    public String did;
    public String discount;
    public String yearnum;
    public String yeardscrp;

    public String toString() {
        return "PaymentDiscountBean[" + "did=" + did + ",discount=" + discount
                + ",yearnum=" + yearnum + ",yeardscrp=" + yeardscrp + "]";
    }

    public String getDiscountName(Context context) {
        return yeardscrp
                + Integer.valueOf(discount)
                / 10
                + context.getResources().getString(
                        R.string.payment_common_discount_zhekou);
    }
}
