package com.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PriorityAlertDecoratorTest {

    private Alert mockAlert;
    private PriorityAlertDecorator priorityAlertDecorator;

    @BeforeEach
    public void setUp() {
        mockAlert = mock(Alert.class);
        when(mockAlert.getPatientId()).thenReturn("12345");
        when(mockAlert.getCondition()).thenReturn("High Blood Pressure");
        when(mockAlert.getTimestamp()).thenReturn(System.currentTimeMillis());

        priorityAlertDecorator = new PriorityAlertDecorator(mockAlert, 5);
    }

    @Test
    public void testSendAlert() {
        priorityAlertDecorator.sendAlert();
        verify(mockAlert).sendAlert(); // Verifies that the sendAlert() method of the decorated alert is called
    }

    @Test
    public void testGetCondition() {
        String expectedCondition = "Priority Level 5: High Blood Pressure";
        assertEquals(expectedCondition, priorityAlertDecorator.getCondition());
    }
}