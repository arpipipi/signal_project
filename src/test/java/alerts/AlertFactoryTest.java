package alerts;

import com.alerts.*;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;

public class AlertFactoryTest {

    @Test
    public void testBloodPressureAlertFactory(){
        // Test the BloodPressureAlertFactory to ensure it creates the correct alert type
        AlertFactory alertFactory = new BloodPressureAlertFactory();
        Alert alert = alertFactory.createAlert("1", "High Blood Pressure", System.currentTimeMillis());

        assertTrue(alert instanceof BloodPressureAlert); // Ensure the alert is of the correct type
        assertEquals("1", alert.getPatientId()); // Validate the patient ID
        assertEquals("High Blood Pressure", alert.getCondition()); // Validate the alert condition
    }

    @Test
    public void testBloodOxygenAlertFactory(){
        // Test the BloodOxygenAlertFactory to ensure it creates the correct alert type
        AlertFactory alertFactory = new BloodOxygenAlertFactory();
        Alert alert = alertFactory.createAlert("1", "Low Blood Oxygen", System.currentTimeMillis());

        assertTrue(alert instanceof BloodOxygenAlert); // Ensure the alert is of the correct type
        assertEquals("1", alert.getPatientId()); // Validate the patient ID
        assertEquals("Low Blood Oxygen", alert.getCondition()); // Validate the alert condition
    }

    @Test
    public void testECGAlertFactory(){
        // Test the ECGAlertFactory to ensure it creates the correct alert type
        AlertFactory alertFactory = new ECGAlertFactory();
        Alert alert = alertFactory.createAlert("1", "ECG is Abnormal", System.currentTimeMillis());

        assertTrue(alert instanceof ECGAlert); // Ensure the alert is of the correct type
        assertEquals("1", alert.getPatientId()); // Validate the patient ID
        assertEquals("ECG is Abnormal", alert.getCondition()); // Validate the alert condition
    }
}
