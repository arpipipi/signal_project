package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * The interface defines a method to generate and output data for a specific patient.
 * Implementations of this interface should define the specific mechanism and format for data generation and output.
 *
 * The cardiovascular data generating module, which includes this interface,
 * simulates or retrieves patient data for testing or analysis.
 */
public interface PatientDataGenerator {

    /**
     * Generates data for a certain patient and outputs it using a given approach.
     * This method is meant to be implemented with certain data generation logic,
     * which could include fetching, computing, or simulating patient data.
     * @param patientId The unique identifier of the patient for whom data is to be generated.
     *                  This identifier is used to specify and retrieve patient-specific information.
     *                  The value should be a positive integer representing a valid patient ID.
     * @param outputStrategy This is the strategy used to outputting the generated data.
     *                       The implementation of this strategy should determine how data is formatted and where it is sent.
     *
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
