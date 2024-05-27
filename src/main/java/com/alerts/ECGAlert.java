package com.alerts;

public class ECGAlert extends Alert{
    public ECGAlert(String patientId, String condition, long timestamp){
        // Initialize the ECGAlert with patientId, condition, and timestamp
        super(patientId, condition, timestamp);
    }
}
