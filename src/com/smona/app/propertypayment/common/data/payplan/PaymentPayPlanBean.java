package com.smona.app.propertypayment.common.data.payplan;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentPayPlanBean extends PaymentItemInfo {
    public String communitycode;
    public String housingbantranscode;
    public String pstartdate;
    public String penddate;
    public String needfare;
    public String needdscrp;

    public String toString() {
        return "PaymentPropertyPayPlanBean[" + ", communitycode="
                + communitycode + ", housingbantranscode="
                + housingbantranscode + ", pstartdate=" + pstartdate
                + ", penddate=" + penddate + ", penddate=" + penddate
                + ", needfare=" + needfare + ", needdscrp=" + needdscrp + "]";
    }
}
