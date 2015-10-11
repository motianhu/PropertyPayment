package com.smona.app.propertypayment.process;

public class PaymentMessageProcessProxy extends PaymentMessageProcess {
    protected PaymentMessageProcess mLocal;
    protected PaymentMessageProcess mNetRequest;
    protected PaymentMessageProcess mNetSubmit;
}
