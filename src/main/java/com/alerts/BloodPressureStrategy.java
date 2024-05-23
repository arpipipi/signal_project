package com.alerts;

public class BloodPressureStrategy implements AlertStrategy {

    @Override
    public boolean checkAlert(double bloodPressure) {

        return bloodPressure > 140 || bloodPressure < 90;
        // Check is the blood pressure is above 140 or below 90, if it is, returns true
    }
}
