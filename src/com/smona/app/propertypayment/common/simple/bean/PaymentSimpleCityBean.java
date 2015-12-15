package com.smona.app.propertypayment.common.simple.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentSimpleCityBean extends PaymentItemInfo {
    public String citycode;
    public String cityname;

    public String toString() {
        return "PaymentSimpleCityBean[citycode: " + citycode + ", cityname: "
                + cityname + "]";
    }
}
