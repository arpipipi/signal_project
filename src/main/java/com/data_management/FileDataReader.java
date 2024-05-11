package com.data_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileDataReader implements DataReader {
    // Provided implementation for the interface DataReader
    private final String directory;
    // String variable that can only be accessed in this class and whatever is assigned
    // to directory cannot be changed

public FileDataReader(String directory) {
    // Declares a public constructor that takes a string called directory as the parameter
    this.directory = directory;
}

@Override // This method intends to override the method (readData) in the interface DataReader
    public void readData(DataStorage dataStorage) { // Public method declaration that does not return anything since it is void
        File direct = new File(directory);
        // Creates a new File object from, and that is also indicating the directory from which the file is to be read.
        // Directory was initialised in constructor

    File[] files = direct.listFiles((d, name) -> name.endsWith(".txt"));
        // Gets all the files that are in the directory and end with ".txt", and consequently stores these files in an array
        // The listFiles methods takes the name of the file as a parameter

        if (files != null) {
            // Checks if the array "files" is not null since the listFiles method can return null if the
            // directory does not exist in the directory or if there is an error

            for (File file: files) {
                try (BufferedReader readers = new BufferedReader(new FileReader(file))) {
                    // Creates a BufferedReader that serves to read through the file.
                    // The BufferedReader is made in the try-with-resources block to guarantee that the file is closed after
                    // the block has been executed.

                    String textLine; // Will store each line of the file
                    while ((textLine = readers.readLine()) != null) { // Persists until the end of the file
                        String[] dataComponents = textLine.split(",");
                        int patientId = Integer.parseInt(dataComponents[0].trim());
                        // In the dataComponents array, the patientId is the first element, and it is then parsed to an
                        // integer, and stored in the variable patientId

                        long time = Long.parseLong(dataComponents[1].trim());
                        // In the dataComponents array, the time is the second element, and it is then parsed to a long,
                        // and stored in the variable time

                        String label = dataComponents[2].trim(); // Trims the third element (Label)
                        double data = Double.parseDouble(dataComponents[3].trim());
                        // Parses fourth element (data) to a double and stores it in the variable data

                        dataStorage.addPatientData(patientId, data, label, time);
                        // Calls the addPatientData method from the DataStorage class to pass the patientId, data, label, and time
                        }
                } catch (IOException e) {
                    System.err.println("Error when reading the file: " + e.getMessage());
                }
            }
        }
    }
}
