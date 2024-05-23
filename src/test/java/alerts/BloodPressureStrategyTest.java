package alerts;

import org.junit.jupiter.api.Test;
import com.alerts.BloodPressureStrategy;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureStrategyTest{
    @Test
    public void testCheckAlert_HighBloodPressure() {
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(155));
        assertFalse(bloodPressureStrategy.checkAlert(130));
    }
    @Test
    public void testCheckAlert_LowBloodPressure() {
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(57));
        assertFalse(bloodPressureStrategy.checkAlert(90));
    }
    @Test
    public void testCheckAlert_NormalBloodPressure() {
        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy();
        assertTrue(bloodPressureStrategy.checkAlert(145));
        assertFalse(bloodPressureStrategy.checkAlert(120));
    }

}
