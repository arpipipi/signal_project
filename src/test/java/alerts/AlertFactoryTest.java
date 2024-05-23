package alerts;

import com.alerts.*;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;

public class AlertFactoryTest {

    @Test
    public void testBloodPressureAlertFactory(){
        AlertFactory alertFactory = new BloodPressureAlertFactory();
        Alert alert = alertFactory.createAlert("1", "High Blood Pressure", System.currentTimeMillis());

        assertTrue(alert instanceof BloodPressureAlert);
        assertEquals("1", alert.getPatientId());
        assertEquals("High Blood Pressure", alert.getCondition());
    }

    @Test
    public void testBloodOxygenAlertFactory(){
        AlertFactory alertFactory = new BloodOxygenAlertFactory();
        Alert alert = alertFactory.createAlert("1", "Low Blood Oxygen", System.currentTimeMillis());

        assertTrue(alert instanceof BloodOxygenAlert);
        assertEquals("1", alert.getPatientId());
        assertEquals("Low Blood Oxygen", alert.getCondition());
    }

    @Test
    public void testECGAlertFactory(){
        AlertFactory alertFactory = new ECGAlertFactory();
        Alert alert = alertFactory.createAlert("1", "ECG is Abnormal", System.currentTimeMillis());

        assertTrue(alert instanceof ECGAlert);
        assertEquals("1", alert.getPatientId());
        assertEquals("ECG is Abnormal", alert.getCondition());
    }
}
