package com.smona.app.propertypayment.phone;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentTypeItem extends PaymentItemInfo {
    public String type_id;
    public String type_name;

    public String toString() {
        return "PaymentTypeItem[iccode: " + iccode + ", answercode: "
                + answercode + ", typeid: " + type_id + ", typename: "
                + type_name + "]";
    }
}
