package data_management;

import com.alerts.AlertGenerator;
import com.cardio_generator.generators.ECGDataGenerator;
import com.data_management.Patient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AlertGeneratorTest {

    @Test
    public void testDetectAbnormalRate() {
        Patient mockPatient = mock(Patient.class);
        when(mockPatient.getPatientId()).thenReturn(1);

        ECGDataGenerator mockECGDataGenerator = mock(ECGDataGenerator.class);
        when(mockECGDataGenerator.getHeartBeatIntervals(anyInt())).thenReturn(new ArrayList<>(Arrays.asList(0.8, 0.9, 1.0, 1.1, 1.2)));
        when(mockECGDataGenerator.getLastHeartRate(anyInt())).thenReturn(60.0);

        AlertGenerator alertGenerator = new AlertGenerator(null);

        alertGenerator.detectAbnormalRate(mockPatient, mockECGDataGenerator);

        verify(mockECGDataGenerator).getHeartBeatIntervals(1);
        verify(mockECGDataGenerator).getLastHeartRate(1);
    }
}
