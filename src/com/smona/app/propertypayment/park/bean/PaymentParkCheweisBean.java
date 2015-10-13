package com.smona.app.propertypayment.park.bean;

import java.util.List;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentParkCheweisBean extends PaymentItemInfo {
    public List<PaymentParkCheweiBean> icobject;

    public String toString() {
        return "PayementPropertyFangchanBean[" + icobject + "]";
    }
}
