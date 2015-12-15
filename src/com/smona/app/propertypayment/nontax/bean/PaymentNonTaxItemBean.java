package com.smona.app.propertypayment.nontax.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentNonTaxItemBean extends PaymentItemInfo {
    public String username;
    public String outid;
    public String paymentname;
    public String payment_addr;
    public String postradeno;
    public String exchg_atm;
    public String accode;
    public boolean isSelected;

    public String toString() {
        return "PaymentNonTaxItemBean[paymentname:" + paymentname + ",postradeno:" + postradeno
                + ",exchg_atm" + exchg_atm + ",accode:" + accode + ",isSelected:"
                + isSelected + "]";
    }
}
