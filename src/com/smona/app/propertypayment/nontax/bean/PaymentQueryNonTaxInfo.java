package com.smona.app.propertypayment.nontax.bean;

import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentQueryNonTaxInfo extends PaymentRequestInfo {
    public String logintype;

    public String toString() {
        return "PaymentQueryNonTaxInfo[logintype:" + logintype + "]";
    }
}
