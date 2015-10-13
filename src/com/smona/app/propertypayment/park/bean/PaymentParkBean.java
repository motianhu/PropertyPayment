package com.smona.app.propertypayment.park.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountsBean;
import com.smona.app.propertypayment.common.data.payplan.PaymentPayPlanBean;

public class PaymentParkBean extends PaymentItemInfo {
    public PaymentDiscountsBean mDiscountBean;
    public PaymentPayPlanBean mPlanBean;

    public boolean finishInit() {
        return mDiscountBean != null && mPlanBean != null;
    }
}
