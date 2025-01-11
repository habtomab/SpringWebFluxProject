package com.tarnsaction._app.request;

public class TransactionRequest {
    private String customerId;
    private String planNumber;
    private double amount;

    // Constructor
    public TransactionRequest(String customerId, String planNumber, double amount) {
        this.customerId = customerId;
        this.planNumber = planNumber;
        this.amount = amount;
    }

    // Default Constructor
    public TransactionRequest() {
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "customerId='" + customerId + '\'' +
                ", planNumber='" + planNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}

