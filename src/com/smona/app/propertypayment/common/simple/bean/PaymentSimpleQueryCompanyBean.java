package com.smona.app.propertypayment.common.simple.bean;

import com.smona.app.propertypayment.process.PaymentRequestInfo;

public class PaymentSimpleQueryCompanyBean extends PaymentRequestInfo {
    public String citycode;

    public String toString() {
        return "PaymentSimpleCompanyBean[citycode: " + citycode + "]";
    }
}
