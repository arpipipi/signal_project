package com.alerts;

public class BloodPressureAlertFactory extends AlertFactory{
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp){
        // Create and return a new BloodPressureAlert
        return new BloodPressureAlert(patientId, condition, timestamp);
    }
}
