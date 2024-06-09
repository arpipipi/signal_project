package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {
    private int priorityLevel;

    public PriorityAlertDecorator(Alert decoratedAlert, int priorityLevel) {
        super(decoratedAlert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void sendAlert() {
        System.out.println("Priority Level: " + priorityLevel + " Alert: " + decoratedAlert.getCondition() + " for Patient ID: " + getPatientId() + " at " + getTimestamp());
        decoratedAlert.sendAlert();
    }

    @Override
    public String getCondition() {
        return "Priority Level " + priorityLevel + ": " + decoratedAlert.getCondition();
    }
}