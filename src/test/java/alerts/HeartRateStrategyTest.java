package alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.alerts.HeartRateStrategy;

public class HeartRateStrategyTest {
    @Test
    public void testCheckAlert_HighHeartRate() {
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(105));
        assertTrue(heartRateStrategy.checkAlert(57));
        assertFalse(heartRateStrategy.checkAlert(100));
        assertFalse(heartRateStrategy.checkAlert(60));
    }

    @Test
    public void testCheckAlert_LowHeartRate() {
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(105));
        assertTrue(heartRateStrategy.checkAlert(57));
        assertFalse(heartRateStrategy.checkAlert(100));
        assertFalse(heartRateStrategy.checkAlert(60));
    }

    @Test
    public void testCheckAlert_NormalHeartRate() {
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy();
        assertTrue(heartRateStrategy.checkAlert(105));
        assertTrue(heartRateStrategy.checkAlert(57));
    }
}
