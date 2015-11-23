package com.smona.app.propertypayment.nontax.bean;

import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public class PaymentNonTaxItemBean extends PaymentItemInfo {
    public String kemu;
    public String leibie;
    public String jine;
    public String bank;
    public boolean isSelected;

    public String toString() {
        return "PaymentNonTaxItemBean[kemu:" + kemu + ",leibie:" + leibie
                + ",jine" + jine + ",bank:" + bank + ",isSelected:"
                + isSelected + "]";
    }
}
