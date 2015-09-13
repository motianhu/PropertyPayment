package com.smona.app.propertypayment.common.data.discount;

import java.util.List;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentDiscountsBean extends PaymentItemInfo {
    public String communitycode;
    public List<PaymentDiscountBean> icobject;

    public String toString() {
        return "PaymentDiscountsBean[" + ", communitycode="
                + communitycode + ", icobject=" + icobject + "]";
    }
}
