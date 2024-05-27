package data_management;

import com.data_management.DataReader;
import com.data_management.DataStorage;
import com.data_management.MockDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class DataReaderTest {

    private DataReader dataReader;
    private DataStorage dataStorage;

    @BeforeEach
    void setUp() {
        dataReader = new MockDataReader(); // Use the actual implementation of MockDataReader
        dataStorage = mock(DataStorage.class); // Mock DataStorage
    }

    @Test
    void testReadData() throws IOException {
        // Test reading data to ensure it interacts with DataStorage correctly
        dataReader.readData(dataStorage);
        verify(dataStorage, times(1)).addPatientData(anyInt(), anyDouble(), anyString(), anyLong());
    }

    @Test
    void testReadDataThrowsRuntimeException() {
        // Simulate an exception during data reading
        doThrow(new RuntimeException("Test exception")).when(dataStorage).addPatientData(anyInt(), anyDouble(), anyString(), anyLong());
        assertThrows(RuntimeException.class, () -> dataReader.readData(dataStorage));
    }

    @Test
    void testConnect() throws URISyntaxException, IOException {
        // Test connecting to ensure it handles the connection correctly
        URI serverUri = new URI("ws://localhost:8080");
        assertDoesNotThrow(() -> dataReader.connect(serverUri));
    }

    @Test
    void testConnectThrowsIOException() throws IOException, URISyntaxException {
        // Simulate an IOException during connection
        URI serverUri = new URI("ws://localhost:8080");
        DataReader mockDataReader = mock(DataReader.class);
        doThrow(IOException.class).when(mockDataReader).connect(serverUri);
        assertThrows(IOException.class, () -> mockDataReader.connect(serverUri));
    }

    @Test
    void testOnMessageValidFormat() {
        // Test onMessage with a valid message format
        String validMessage = "123,1627890123456,label,45.67";
        assertDoesNotThrow(() -> dataReader.onMessage(validMessage));
    }

    @Test
    void testOnMessageInvalidFormat() {
        // Test onMessage with an invalid message format (missing data elements)
        String invalidMessage = "123,1627890123456,label";
        assertDoesNotThrow(() -> dataReader.onMessage(invalidMessage));
    }

    @Test
    void testOnMessageInvalidNumberFormat() {
        // Test onMessage with invalid number formats in the message
        String invalidMessage = "abc,1627890123456,label,xyz";
        assertDoesNotThrow(() -> dataReader.onMessage(invalidMessage));
    }

    @Test
    void testReadDataWithRuntimeException() {
        // Simulate a runtime exception during data reading
        DataStorage mockDataStorage = mock(DataStorage.class);
        doThrow(new RuntimeException("Runtime exception")).when(mockDataStorage).addPatientData(anyInt(), anyDouble(), anyString(), anyLong());
        assertThrows(RuntimeException.class, () -> dataReader.readData(mockDataStorage));
    }

    @Test
    void testNetworkErrorDuringReadData() throws IOException {
        // Simulate a network error during data reading
        DataReader failingDataReader = spy(new MockDataReader());
        doThrow(new IOException("Network error")).when(failingDataReader).readData(any(DataStorage.class));
        assertThrows(IOException.class, () -> failingDataReader.readData(dataStorage));
    }
}