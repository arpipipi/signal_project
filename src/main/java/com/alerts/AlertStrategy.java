package com.alerts;

public interface AlertStrategy {

    // This method's purpose is to verify if the value is in the normal range
    boolean checkAlert(double value);
}

