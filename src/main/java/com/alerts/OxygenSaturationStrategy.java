package com.alerts;

public class OxygenSaturationStrategy implements AlertStrategy {

    @Override
    public boolean checkAlert(double oxygenSaturation) {
        
        return oxygenSaturation < 95;
        // Checks if the oxygen saturation is below 95, if it is, returns true
    }
}
