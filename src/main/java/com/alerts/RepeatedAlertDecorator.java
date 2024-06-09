package com.alerts;

import java.util.logging.Logger;
import java.util.logging.Level;

public class RepeatedAlertDecorator extends AlertDecorator {
    private static final Logger logger = Logger.getLogger(RepeatedAlertDecorator.class.getName());
    private int repeatCount;
    private long interval;

    public RepeatedAlertDecorator(Alert decoratedAlert, int repeatCount, long interval) {
        super(decoratedAlert);
        this.repeatCount = repeatCount;
        this.interval = interval;
    }

    public void sendAlert() {
        for (int i = 0; i < repeatCount; i++) {
            logger.info("Repeated Alert: " + getCondition() + " for Patient ID: " + getPatientId() + " at " + getTimestamp());
            decoratedAlert.sendAlert();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Interrupted Exception", e);
            }
        }
    }
}