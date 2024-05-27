package com.alerts;

public class ECGAlertFactory extends AlertFactory{
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp){
        // Create and return a new ECGAlert
        return new ECGAlert(patientId, condition, timestamp);
    }
}
