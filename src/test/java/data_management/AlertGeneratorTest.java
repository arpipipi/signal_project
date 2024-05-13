package data_management;

import com.alerts.AlertGenerator;
import com.cardio_generator.generators.ECGDataGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class AlertGeneratorTest {

    @Test
    public void testDetectAbnormalRate() {

        Patient mockPatient = Mockito.mock(Patient.class);
        Mockito.when(mockPatient.getPatientId()).thenReturn(1);

        ECGDataGenerator mockECGDataGenerator = Mockito.mock(ECGDataGenerator.class);
        Mockito.when(mockECGDataGenerator.getHeartBeatIntervals(anyInt())).thenReturn(new ArrayList<>(Arrays.asList(0.8, 0.9, 1.0, 1.1, 1.2)));
        Mockito.when(mockECGDataGenerator.getLastHeartRate(anyInt())).thenReturn(60.0);

        DataStorage mockDataStorage = Mockito.mock(DataStorage.class);
        AlertGenerator alertGenerator = new AlertGenerator(mockDataStorage);

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

    @Test
    public void testHypotensiveHypoxemiaAlertTrigger() {
        DataStorage dataStorage = mock(DataStorage.class);
        List<PatientRecord> lowBpRecords = Collections.singletonList(
                new PatientRecord(1, 85.0, "SystolicPressure", System.currentTimeMillis())
        );
        List<PatientRecord> lowSaturationRecords = Collections.singletonList(
                new PatientRecord(1, 90.0, "Saturation", System.currentTimeMillis())
        );

        when(dataStorage.getRecords(1, anyLong(), anyLong()))
                .thenReturn(lowBpRecords)
                .thenReturn(lowSaturationRecords);

        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.checkHypotensiveHypoxemia(new Patient(1));

        // Assuming `triggerAlert` is a public method you can verify
        verify(alertGenerator).triggerAlert(Mockito.any());
    }

}
