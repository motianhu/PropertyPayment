package com.smona.app.propertypayment.common.data.payplan;

import java.util.List;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentPayPlanDetailsBean extends PaymentItemInfo {

    public String communitycode;
    public String housingbantranscode;
    public List<PaymentPayPlanDetailBean> icobject;

    public String toString() {
        return "PaymentPropertyPayPlanBean[" + ", communitycode="
                + communitycode + ", housingbantranscode="
                + housingbantranscode + ", icobject=" + icobject + "]";
    }
}
