package com.alerts;

public abstract class AlertFactory {
    /**
     * Abstract method to create an alert.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was created
     * @return a new Alert instance
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
