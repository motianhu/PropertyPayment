package com.smona.app.propertypayment.property.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.data.discount.PaymentDiscountsBean;
import com.smona.app.propertypayment.common.data.payplan.PaymentPayPlanBean;

public class PaymentPropertyBean extends PaymentItemInfo {
    public PaymentDiscountsBean mDiscountBean;
    public PaymentPayPlanBean mPlanBean;

    public boolean finishInit() {
        return mDiscountBean != null && mPlanBean != null;
    }
}
