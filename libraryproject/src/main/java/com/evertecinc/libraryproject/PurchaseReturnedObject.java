package com.evertecinc.libraryproject;

public class PurchaseReturnedObject {

    private String completed;
    private String cartReferenceId;
    private String dailyTransactionId;
    private String transactionReference;

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getCartReferenceId() {
        return cartReferenceId;
    }

    public void setCartReferenceId(String cartReferenceId) {
        this.cartReferenceId = cartReferenceId;
    }

    public String getDailyTransactionId() {
        return dailyTransactionId;
    }

    public void setDailyTransactionId(String dailyTransactionId) {
        this.dailyTransactionId = dailyTransactionId;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }
}
