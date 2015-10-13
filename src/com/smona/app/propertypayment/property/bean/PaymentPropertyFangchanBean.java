package com.smona.app.propertypayment.property.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentPropertyFangchanBean extends PaymentItemInfo {
    public String communitycode;
    public String communityname;
    public String housingbancode;
    public String housingbanname;
    public String housingbantranscode;
    public String housingbantransname;
    public String propertyname;
    public String payaccount;

    public String toString() {
        return "PayementPropertyFangchanBean[" + "communitycode="
                + communitycode + "communityname=" + communityname
                + "housingbancode=" + housingbancode + "housingbanname="
                + housingbanname + "housingbantranscode=" + housingbantranscode
                + "housingbantransname=" + housingbantransname
                + "propertyname=" + propertyname + "payaccount=" + payaccount
                + "]";
    }
    
    public String getFangchanAddr() {
        return communityname +  housingbanname + housingbantransname;
    }
}
