package com.smona.app.propertypayment.heat.bean;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleFeeInfoBean;

public class PaymentHeatFeeInfoBean extends PaymentSimpleFeeInfoBean {
    public String heat_addr;

    public String toString() {
        return "PaymentPowerQueryInfoBean[" + ", consno=" + consno
                + ", trans_name=" + trans_name + ", water_addr=" + heat_addr
                + ", exchg_atm=" + exchg_atm + ", postradeno=" + postradeno
                + ", return_code=" + return_code + ", return_msg=" + return_msg
                + "]";
    }
}
