package com.smona.app.propertypayment.water.bean;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleFeeInfoBean;

public class PaymentWaterFeeInfoBean extends PaymentSimpleFeeInfoBean {
    public String water_addr;

    public String toString() {
        return "PaymentPowerQueryInfoBean[" + ", consno=" + consno
                + ", trans_name=" + trans_name + ", water_addr=" + water_addr
                + ", exchg_atm=" + exchg_atm + ", postradeno=" + postradeno
                + ", return_code=" + return_code + ", return_msg=" + return_msg
                + "]";
    }
}
