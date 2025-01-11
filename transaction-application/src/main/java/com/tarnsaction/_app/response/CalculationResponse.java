package com.tarnsaction._app.response;
public class CalculationResponse {
    private String customerId;
    private String planNumber;
    private double calculatedFee;
    private boolean successful;

    // Constructor
    public CalculationResponse(String customerId, String planNumber, double calculatedFee, boolean successful) {
        this.customerId = customerId;
        this.planNumber = planNumber;
        this.calculatedFee = calculatedFee;
        this.successful = successful;
    }

    // Default Constructor
    public CalculationResponse() {
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

    // Update the getter for calculatedFee to match the expected method name
    public double getFee() {
        return calculatedFee;
    }

    public void setCalculatedFee(double calculatedFee) {
        this.calculatedFee = calculatedFee;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return "CalculationResponse{" +
                "customerId='" + customerId + '\'' +
                ", planNumber='" + planNumber + '\'' +
                ", calculatedFee=" + calculatedFee +
                ", successful=" + successful +
                '}';
    }
}

