package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.io.IOException;
import java.net.URI;

public class WebSocketDataReader extends WebSocketClient implements DataReader {
    private DataStorage dataStorage;

    public WebSocketDataReader(URI serverUri, DataStorage dataStorage){
        super(serverUri);
        this.dataStorage = dataStorage;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connection was Opened");
    }

    @Override
    public void onMessage(String message) {
        String[] dataElements = message.split(",");
        int patientId = Integer.parseInt(dataElements[0].trim());
        long time = Long.parseLong(dataElements[1].trim());
        String label = dataElements[2].trim();
        double data = Double.parseDouble(dataElements[3].trim());

        dataStorage.addPatientData(patientId, data, label, time);
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection was Closed");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {}

    @Override
    public void connect(URI serverUri) throws IOException {
        this.connect(); // Connects to the server by calling the connect method from the WebSocketClient class
    }
}
