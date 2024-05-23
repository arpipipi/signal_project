package alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.alerts.OxygenSaturationStrategy;

public class OxygenSaturationStrategyTest {
    @Test
    public void testCheckAlert_LowOxygenSaturation(){
        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy();
        assertTrue(oxygenSaturationStrategy.checkAlert(90));
        assertFalse(oxygenSaturationStrategy.checkAlert(100));
    }
    @Test
    public void testCheckAlert_NormalOxygenSaturation(){
        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy();
        assertFalse(oxygenSaturationStrategy.checkAlert(98));
    }
}
