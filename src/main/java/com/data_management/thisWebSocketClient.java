package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class thisWebSocketClient extends WebSocketClient{

    // WebSockets offer a 2-way communication channel between both the client and the server, solely over TCP connections
    // Data can be sent and received in real time between both agents
    // TCP connection = connection between two devices over the internet
    // A connection is established and maintained until after the devices have at each end have completed the data transfer or
    // message exchange, and only then the connection is closed
    // It determines how the data will be broken down into packets (smaller units of data with a size that allows them to be
    // managed efficiently by networks) that can be delivered by the networks, sends packets to and accepts packets from
    // the network layer (layer that manages the actual transmission of data over the network). So, when the layer receives
    // packets from the other end of the connection, they are passed to TCP.
    // It also leads flow control to evade the sender from overwhelming the agent receiving the data, so if the receiver is not
    // ready to get more data (maybe because it is still processing the previous data, it can ask the sender to slow down and
    // TCP takes charge over this communication between the sender and receiver
    // Furthermore, it manages retransmission of dropped (lost) packets and garbled (corrupted) packets
    // WebSockets require a dependable connection, TCP is the candidate that offers this dependability

    public thisWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) { // When the connection is opened
        System.out.println("Connection was Opened"); // Prints the message "Connection was Opened"
    }

    @Override
    public void onMessage(String message) {
        System.out.println("A Message was Received: " + message); // Prints the message that was received
        String[] dataElements = message.split(","); // Splits up the message into its individual components
        if (dataElements.length != 4) { // If the message does not have 4 elements
            System.out.println("Format of the Message is Invalid " + message); // Prints that the message is not in the correct format
        }
        try { // Tries to parse the message into its individual components
            int patiendId = Integer.parseInt(dataElements[0].trim()); // Parses the patient ID
            long time = Long.parseLong(dataElements[1].trim()); // Parses the time
            String label = dataElements[2].trim(); // Parses the label
            double data = Double.parseDouble(dataElements[3].trim()); // Parses the data
        } catch (NumberFormatException e) {
            System.out.println("Format of the Data is Invalid: " + message);
            // If the data is not in the correct format, it prints an error message
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) { // When the connection is closed
        System.out.println("Connection was Closed"); // The message "Connection was Closed" is printed
    }

    @Override
    public void onError(Exception ex) { // An error has occurred
        System.err.println("The following error has occurred: " + ex.getMessage()); // Error message is printed
    }

    public static void main(String[] args) throws URISyntaxException {
            WebSocketClient client = new thisWebSocketClient(new URI("ws://localhost:8080"));
            // A new WebSocketClient object is made with the URI
            client.connect(); // Client is connected to the server
    }
}

