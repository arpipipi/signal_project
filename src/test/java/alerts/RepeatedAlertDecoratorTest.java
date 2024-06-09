package com.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

public class RepeatedAlertDecoratorTest {

    private Alert mockAlert;
    private RepeatedAlertDecorator repeatedAlertDecorator;

    @BeforeEach
    public void setUp() {
        mockAlert = mock(Alert.class);
        when(mockAlert.getPatientId()).thenReturn("12345");
        when(mockAlert.getCondition()).thenReturn("High Blood Pressure");
        when(mockAlert.getTimestamp()).thenReturn(System.currentTimeMillis());

        repeatedAlertDecorator = new RepeatedAlertDecorator(mockAlert, 3, 10);
    }

    @Test
    public void testSendAlert() throws InterruptedException {
        repeatedAlertDecorator.sendAlert();
        verify(mockAlert, times(3)).sendAlert(); // Verifies that the sendAlert() method is called the specified number of times
    }
}