package alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.alerts.OxygenSaturationStrategy;

public class OxygenSaturationStrategyTest {
    @Test
    public void testCheckAlert_LowOxygenSaturation(){
        // Test the alert generation for low oxygen saturation levels
        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy();
        assertTrue(oxygenSaturationStrategy.checkAlert(90)); // Should trigger an alert
        assertFalse(oxygenSaturationStrategy.checkAlert(100)); // Should not trigger an alert
    }
    @Test
    public void testCheckAlert_NormalOxygenSaturation(){
        // Test the alert generation for normal oxygen saturation levels
        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy();
        assertFalse(oxygenSaturationStrategy.checkAlert(98)); // Should not trigger an alert
    }
}
