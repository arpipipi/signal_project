package com.alerts;

public class BloodPressureAlert extends Alert{
    public BloodPressureAlert(String patientId, String condition, long timestamp){
        // Initialize the BloodPressureAlert with patientId, condition, and timestamp
        super(patientId, condition, timestamp);
    }
}
