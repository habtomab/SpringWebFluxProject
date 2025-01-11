package com.calculation_app.respose;

public class EligibilityResponse {
    private String customerId;
    private String planNumber;
    private boolean eligible;

    // Constructor
    public EligibilityResponse(String customerId, String planNumber, boolean eligible) {
        this.customerId = customerId;
        this.planNumber = planNumber;
        this.eligible = eligible;
    }

    // Default Constructor
    public EligibilityResponse() {
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

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    @Override
    public String toString() {
        return "EligibilityResponse{" +
                "customerId='" + customerId + '\'' +
                ", planNumber='" + planNumber + '\'' +
                ", eligible=" + eligible +
                '}';
    }
}
