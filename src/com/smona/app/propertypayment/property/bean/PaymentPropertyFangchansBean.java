package com.smona.app.propertypayment.property.bean;

import java.util.List;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentPropertyFangchansBean extends PaymentItemInfo {
    public List<PaymentPropertyFangchanBean> icobject;

    public String toString() {
        return "PayementPropertyFangchanBean[" + icobject + "]";
    }
}
