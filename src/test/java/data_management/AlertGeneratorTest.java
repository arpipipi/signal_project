package data_management;

import com.alerts.AlertGenerator;
import com.cardio_generator.generators.ECGDataGenerator;
import com.data_management.DataStorage;
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

        @Test
        public void testIncreasingTrendAlert() {
        // Create a real DataStorage
        DataStorage dataStorage = new DataStorage();
        // Add the patient records to the data storage
        dataStorage.addPatientData(1, 100.00, "BloodPressure", System.currentTimeMillis() - 3000);
        dataStorage.addPatientData(1, 110.00, "BloodPressure", System.currentTimeMillis() - 2000);
        dataStorage.addPatientData(1, 120.00, "BloodPressure", System.currentTimeMillis() - 1000);

        // Create an AlertGenerator with the real DataStorage
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);

        // Generate an alert for the patient
        alertGenerator.trendAlert(dataStorage.getRecords(1, System.currentTimeMillis() - 60000, System.currentTimeMillis()));
    }

        @Test
        public void testDecreasingTrendAlert() {
        // Create a real DataStorage
        DataStorage dataStorage = new DataStorage();

        // Add the patient records to the data storage
        dataStorage.addPatientData(1, 120.00, "BloodPressure", System.currentTimeMillis() - 3000);
        dataStorage.addPatientData(1, 110.00, "BloodPressure", System.currentTimeMillis() - 2000);
        dataStorage.addPatientData(1, 100.00, "BloodPressure", System.currentTimeMillis() - 1000);

        // Create an AlertGenerator with the real DataStorage
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);

        // Generate an alert for the patient
        alertGenerator.trendAlert(dataStorage.getRecords(1, System.currentTimeMillis() - 60000, System.currentTimeMillis()));
    }
}
