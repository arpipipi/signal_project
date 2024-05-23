package com.alerts;

public class HeartRateStrategy implements AlertStrategy {

    @Override
    public boolean checkAlert(double heartRate) {

        return heartRate > 100 || heartRate < 60;
        // Checks if the heart rate is above 100 or below 60, if it is, returns true
    }
}
