package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Overview: This class is responsible for establishing and maintaining a TCP server that
 * listens for client connections and sends out data formatted as strings. It handles the networking
 * aspects, allowing data to be sent reliably over network connections.
 * <p>
 * Usage: Used in environments where data needs to be streamed to a client application over
 * the network. It is particularly useful in real-time data monitoring or logging applications where
 * data is consumed by external systems or software. Initialize this class with the desired server port,
 * and it will handle client connections and data transmission automatically.
 */
public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Creates a TCP server that listens on a specified port. Once a client connects, it sets up
     * a stream to send data to the client. It handles client connections in a separate thread to
     * avoid blocking the main application flow.
     *
     * @param port the port number on which the server will listen for incoming client connections.
     */
    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends formatted patient data over TCP to a connected client. The data includes
     * identifiers and measurements formatted into a single string.
     *
     * @param patientId The ID of the patient whose data is being sent.
     * @param timestamp The timestamp when the data was collected, in milliseconds since the Unix epoch.
     * @param label A label indicating the type of data, such as "blood pressure" or "heart rate".
     * @param data The actual data as a string.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
