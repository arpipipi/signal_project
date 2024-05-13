package data_management;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public class PatientTest {
    private Patient patient;

    @BeforeAll
    public void setUp() {
        patient = new Patient(123); // A Patient with a test ID
    }


    @Test
    public void testAddRecord() {
        patient.addRecord(98.6, "Temperature", 1610000000000L);
        List<PatientRecord> records = patient.getRecords(1610000000000L, 1610000000000L);
        assertEquals(1, records.size());
        assertEquals(98.6, records.get(0).getMeasurementValue(), 0.0);
        assertEquals("Temperature", records.get(0).getRecordType());
        assertEquals(1610000000000L, records.get(0).getTimestamp());
    }

    @Test
    public void tastGetRecordsWithinRange() {
        patient.addRecord(76, "HeartRate", 1609459200000L); // Jan 1, 2021 00:00:00 GMT
        patient.addRecord(80, "HeartRate", 1609545600000L); // Jan 2, 2021 00:00:00 GMT
        patient.addRecord(78, "HeartRate", 1609632000000L); // Jan 3, 2021 00:00:00 GMT
        List<PatientRecord> results = patient.getRecords(1609459200000L, 1609632000000L);
        assertEquals(3, results.size()); // Expecting three records
    }

    @Test
    public void testGetRecordsOutsideRange() {
        patient.addRecord(120, "BloodPressure", 1609459200000L);
        patient.addRecord(121, "BloodPressure", 1609545600000L);
        List<PatientRecord> results = patient.getRecords(1609632000001L, 1609718400000L);
        assertEquals(0, results.size()); // Expecting no Records
    }

    @Test
    public void testGetRecordsWithNoRecordsAdded() {
        List<PatientRecord> results = patient.getRecords(1609459200000L, 1609632000000L);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testGetRecordsWithEndTimeBeforeStartTime() {
        patient.addRecord(100, "HeartRate", 1609459200000L);
        List<PatientRecord> results = patient.getRecords(1609632000000L, 1609459200000L);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testAddRecordWithBoundaryTimestamps() {
        patient.addRecord(130, "HeartRate", Long.MAX_VALUE);
        patient.addRecord(60, "HeartRate", Long.MIN_VALUE);
        List<PatientRecord> maxTimeRecord = patient.getRecords(Long.MAX_VALUE, Long.MAX_VALUE);
        List<PatientRecord> minTimeRecord = patient.getRecords(Long.MIN_VALUE, Long.MIN_VALUE);
        assertEquals(1, maxTimeRecord.size());
        assertEquals(Long.MAX_VALUE, maxTimeRecord.get(0).getTimestamp());
        assertEquals(1, minTimeRecord.size());
        assertEquals(Long.MIN_VALUE, minTimeRecord.get(0).getTimestamp());
    }
}
