package com.smona.app.propertypayment.heat.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentHeatQueryUserBean extends PaymentItemInfo {
    public String consno;
    public String trans_name;
    public String elec_addr;
    public String exchg_atm;
    public String postradeno;
    public String return_code;
    public String return_msg;
    
    public String toString() {
        return "PaymentPowerQueryInfoBean[" + ", consno=" + consno
                + ", trans_name=" + trans_name + ", elec_addr=" + elec_addr
                + ", exchg_atm=" + exchg_atm + ", postradeno=" + postradeno
                + ", return_code=" + return_code + ", return_msg=" + return_msg
                + "]";
    }
}
