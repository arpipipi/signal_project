package com.cardio_generator.generators;

import com.alerts.AlertGenerator;

public class HealthDataGenerator {

    private AlertGenerator alertGenerator;

    public HealthDataGenerator(AlertGenerator alertGenerator) {
        this.alertGenerator = alertGenerator;
    }

    public void pressOnAlertButton(int patientId) {
        alertGenerator.triggerManualAlert(patientId);
    }

}

