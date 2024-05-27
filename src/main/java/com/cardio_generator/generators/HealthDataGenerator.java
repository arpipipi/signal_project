package com.cardio_generator.generators;

import com.alerts.AlertGenerator;

public class HealthDataGenerator {

    private AlertGenerator alertGenerator;

    // Constructor to initialize HealthDataGenerator with the alert generator
    public HealthDataGenerator(AlertGenerator alertGenerator) {
        this.alertGenerator = alertGenerator;
    }

    // Method to manually trigger an alert for a specified patient
    public void pressOnAlertButton(int patientId) {
        alertGenerator.triggerManualAlert(patientId);
    }

}

