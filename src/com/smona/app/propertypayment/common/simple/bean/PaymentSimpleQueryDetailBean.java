package com.smona.app.propertypayment.common.simple.bean;

import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentSimpleQueryDetailBean extends PaymentRequestInfo {
    public String con_so;

    public String toString() {
        return "PaymentSimpleQueryDetailBean[citycode: " + con_so + "]";
    }
}
