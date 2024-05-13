package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.data_management.DataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.List;

class DataStorageTest {

    private DataStorage storage;

    @BeforeEach
    void setUp() {
        storage = new DataStorage();
    }

    @Test
    void testAddAndGetRecords() {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        //DataStorage storage = new DataStorage();

        // Testing if the records are added and retrieved accurately for a given patient
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }

    @Test
    void testBoundaryConditions() {
        // Testing data retrieval at the precise start and end of the time boundary
        storage.addPatientData(1, 150.0, "Temperature", 1714376789050L);
        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789050L);

        assertEquals(1, records.size(), "Should retrieve one record at the boundary timestamp");
    }

    @Test
    void testNonExistentPatient() {
        // Verifying that querying for a non-existent patient returns an empty list
        List<PatientRecord> records = storage.getRecords(999, 1714376780000L, 1714376790000L);
        assertTrue(records.isEmpty(), "No records should be returned for a non-existent patient");
    }

    @Test
    void testAddMultipleRecordsSameTimestamp() {
        // Ensure that multiple records with the same timestamp are handled correctly
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789050L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789050L);
        assertEquals(2, records.size(), "Should handle multiple records with the same timestamp");
    }
}
