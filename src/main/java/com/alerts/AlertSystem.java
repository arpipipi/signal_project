package com.alerts;

public class AlertSystem {
    public static void main(String[] args) {
        Alert basicAlert = new Alert("12345", "High Blood Pressure", System.currentTimeMillis());

        // Repeated Alert
        RepeatedAlertDecorator repeatedAlert = new RepeatedAlertDecorator(basicAlert, 3, 1000);
        repeatedAlert.sendAlert();

        // Priority Alert
        PriorityAlertDecorator priorityAlert = new PriorityAlertDecorator(basicAlert, 5);
        priorityAlert.sendAlert();

        // Combined Alert
        RepeatedAlertDecorator combinedAlert = new RepeatedAlertDecorator(new PriorityAlertDecorator(basicAlert, 5), 3, 1000);
        combinedAlert.sendAlert();
    }
}