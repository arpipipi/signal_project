package data_management;

import com.data_management.thisWebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;


class ThisWebSocketClientTest {

    private thisWebSocketClient client;
    private ServerHandshake handshake;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize the WebSocket client and mock the handshake
        client = new thisWebSocketClient(new URI("ws://localhost:8080"));
        handshake = Mockito.mock(ServerHandshake.class);
    }

    @Test
    void testOnOpen() {
        // Test the onOpen method to ensure it handles the connection opening correctly
        client.onOpen(handshake);
    }

    @Test
    void testOnMessageValidFormat() {
        // Test onMessage with a valid message format
        client.onMessage("123,1627890123456,label,45.67");
    }

    @Test
    void testOnMessageInvalidFormat() {
        // Test onMessage with an invalid message format (missing data elements)
        client.onMessage("123,1627890123456,label");
    }

    @Test
    void testOnMessageInvalidNumberFormat() {
        // Test onMessage with invalid number formats in the message
        client.onMessage("abc,1627890123456,label,xyz");
    }

    @Test
    void testOnClose() {
        // Test the onClose method to ensure it handles the connection closing correctly
        client.onClose(1000, "Normal closure", true);
    }

    @Test
    void testOnError() {
        // Test the onError method to ensure it handles errors correctly
        client.onError(new Exception("Test exception"));
    }

    @Test
    void testConnectionFailure() throws URISyntaxException {
        // Simulate a connection failure
        thisWebSocketClient failingClient = spy(new thisWebSocketClient(new URI("ws://invalid:8080")));
        doThrow(new RuntimeException("Connection failed")).when(failingClient).connect();
        try {
            failingClient.connect();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testDataTransmissionFailure() throws URISyntaxException {
        // Simulate a data transmission failure
        thisWebSocketClient failingClient = spy(new thisWebSocketClient(new URI("ws://localhost:8080")));
        doThrow(new RuntimeException("Data transmission failed")).when(failingClient).send(anyString());
        try {
            failingClient.send("Test message");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testConnectionLoss() {
        // Simulate an unexpected connection loss
        client.onClose(1006, "Connection lost", true);
    }
}