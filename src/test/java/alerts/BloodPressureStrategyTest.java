package alerts;

import org.junit.jupiter.api.Test;
import com.alerts.BloodPressureStrategy;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureStrategyTest{
    @Test
    public void testCheckAlert_HighBloodPressure() {
        // Test the alert generation for high blood pressure levels
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(155)); // Should trigger an alert for high blood pressure
        assertFalse(bloodPressureStrategy.checkAlert(130)); // Should not trigger an alert for normal blood pressure
    }
    @Test
    public void testCheckAlert_LowBloodPressure() {
        // Test the alert generation for low blood pressure levels
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(57)); // Should trigger an alert for low blood pressure
        assertFalse(bloodPressureStrategy.checkAlert(90)); // Should not trigger an alert for normal blood pressure
    }
    @Test
    public void testCheckAlert_NormalBloodPressure() {
        // Test the alert generation for normal blood pressure levels
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(145)); // Should trigger an alert for high normal blood pressure
        assertFalse(bloodPressureStrategy.checkAlert(120)); // Should not trigger an alert for ideal blood pressure
    }

}
