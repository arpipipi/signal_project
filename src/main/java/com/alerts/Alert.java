package com.alerts;

// Represents an alert
public class Alert {
    private String patientId; // The ID of the patient for whom the alert is generated
    private String condition; // The condition that triggered the alert
    private long timestamp; // The time when the alert was generated

    // Constructor to initialize the Alert with patientId, condition, and timestamp
    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    // Getter method to retrieve the patient ID
    public String getPatientId() {
        return patientId;
    }

    // Getter method to retrieve the condition that triggered the alert
    public String getCondition() {
        return condition;
    }

    // Getter method to retrieve the timestamp of the alert
    public long getTimestamp() {
        return timestamp;
    }
}
