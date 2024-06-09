package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {
    private int priorityLevel;

    public PriorityAlertDecorator(Alert decoratedAlert, int priorityLevel) {
        super(decoratedAlert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void sendAlert() {
        System.out.println("Priority Level: " + priorityLevel + " Alert: " + getCondition() + " for Patient ID: " + getPatientId() + " at " + getTimestamp());
    }

    @Override
    public String getCondition() {
        return "Priority Level " + priorityLevel + ": " + super.getCondition();
    }
}