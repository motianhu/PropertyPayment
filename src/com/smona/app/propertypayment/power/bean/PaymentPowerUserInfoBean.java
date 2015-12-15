package com.smona.app.propertypayment.power.bean;

import com.smona.app.propertypayment.common.simple.bean.PaymentSimpleFeeInfoBean;

public class PaymentPowerUserInfoBean extends PaymentSimpleFeeInfoBean {
    public String elec_addr;

    public String toString() {
        return "PaymentPowerQueryInfoBean[" + ", consno=" + consno
                + ", trans_name=" + trans_name + ", elec_addr=" + elec_addr
                + ", exchg_atm=" + exchg_atm + ", postradeno=" + postradeno
                + ", return_code=" + return_code + ", return_msg=" + return_msg
                + "]";
    }
}
