package alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.alerts.HeartRateStrategy;

public class HeartRateStrategyTest {
    @Test
    public void testCheckAlert_HighHeartRate() {
        // Test the alert generation for high heart rate levels
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(105)); // Should trigger an alert for high heart rate
        assertTrue(heartRateStrategy.checkAlert(57)); // Should trigger an alert for low heart rate
        assertFalse(heartRateStrategy.checkAlert(100)); // Should not trigger an alert
        assertFalse(heartRateStrategy.checkAlert(60)); // Should not trigger an alert
    }

    @Test
    public void testCheckAlert_LowHeartRate() {
        // Test the alert generation for low heart rate levels
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(55)); // Should trigger an alert for low heart rate
        assertTrue(heartRateStrategy.checkAlert(30)); // Should trigger an alert for very low heart rate
        assertFalse(heartRateStrategy.checkAlert(100)); // Should not trigger an alert for high heart rate
        assertFalse(heartRateStrategy.checkAlert(95)); // Should not trigger an alert for normal heart rate
    }

    @Test
    public void testCheckAlert_NormalHeartRate() {
        // Test the alert generation for normal heart rate levels
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(102)); // Should trigger an alert for slightly high heart rate
        assertTrue(heartRateStrategy.checkAlert(58)); // Should trigger an alert for slightly low heart rate
    }
}
