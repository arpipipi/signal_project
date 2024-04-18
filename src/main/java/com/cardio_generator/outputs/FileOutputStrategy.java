package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The fileOutputStrategy class implements the OutputStrategy interface to save patient data to files.
 * It's designed to create and manage files for different types of data labels, storing each type in its
 * own file within a specified base directory.
 */
public class FileOutputStrategy implements OutputStrategy { // Renamed class to UpperCamelCase
    // Also renamed the filename to match the class name

    private String baseDirectory; // Renamed variable to LowerCamelCase

    /** A map to keep track of file paths for different data labels */
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();
    //renamed

    /**
     * Creates a new file output strategy with a specified directory for storing files.
     *
     * @param baseDirectory The directory where data files will be saved.
     */
    public FileOutputStrategy(String baseDirectory) { // Renamed variable baseDirectory to LowerCamelCase

        this.baseDirectory = baseDirectory; // Renamed baseDirectory to LowerCamelCase from UpperCamelCase
    }

    /**
     * Outputs data to a file specific to the data type (label). It ensures that the directory exists,
     * and if not, it attempts to create it. Data is then written to a file named by the label.
     *
     * @param patientId The ID of the patient, used to identify whose data it is.
     * @param timestamp The time when the data was recorded, given in milliseconds since Jan 1, 1970.
     * @param label A label that describes the type of data, which also determines the file name.
     * @param data The actual data being written, formatted as a string.
     */
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