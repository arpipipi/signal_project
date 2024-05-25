package com.data_management;

import java.io.IOException;
import java.net.URI;

public class MockDataReader implements DataReader {

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        // Call addPatientData to trigger the mock behavior
        dataStorage.addPatientData(123, 45.67, "label", 1627890123456L);
    }

    @Override
    public void connect(URI serverUri) throws IOException {
        // Mock implementation
    }

    @Override
    public void onMessage(String message) {
        // Mock implementation
    }
}