package com.tarnsaction._app.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private String planNumber;
    private double amount;
    private boolean success;
    private String message;

    // Default Constructor
    public Transaction() {
    }

    // Constructor
    public Transaction(String customerId, String planNumber, double amount, boolean success, String message) {
        this.customerId = customerId;
        this.planNumber = planNumber;
        this.amount = amount;
        this.success = success;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", planNumber='" + planNumber + '\'' +
                ", amount=" + amount +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
