package com.smona.app.propertypayment.park.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentParkCheweiBean extends PaymentItemInfo {
    public String parknum;
    public String cardnum;
    public String propertyname;
    public String communitycode;
    public String payaccount;

    public String toString() {
        return "PayementPropertyFangchanBean[" + "parknum=" + parknum
                + "cardnum=" + cardnum + ",communitycode=" + communitycode
                + "propertyname=" + propertyname + "payaccount=" + payaccount
                + "]";
    }

    public String getCarInfo() {
        return cardnum;
    }
}
