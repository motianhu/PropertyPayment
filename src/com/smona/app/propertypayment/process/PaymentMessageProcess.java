package com.smona.app.propertypayment.process;

public abstract class PaymentMessageProcess {

    public interface IQuestCallback {
        void onResult(boolean success, String content);
    }
}
