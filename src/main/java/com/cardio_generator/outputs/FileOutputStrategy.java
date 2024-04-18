package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

public class FileOutputStrategy implements OutputStrategy { // Renamed class to UpperCamelCase
    // Also renamed the filename to match the class name

    private String baseDirectory; // Renamed variable to LowerCamelCase

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();
    //renamed

    public FileOutputStrategy(String baseDirectory) { // Renamed variable baseDirectory to LowerCamelCase

        this.baseDirectory = baseDirectory; // Renamed baseDirectory to LowerCamelCase from UpperCamelCase
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory)); // Renamed baseDirectory to LowerCamelCase
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String FilePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());
        // Renamed variables fileMap and baseDirectory to LowerCamelCase from UpperCamelCase

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(FilePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + FilePath + ": " + e.getMessage());
        }
    }
}