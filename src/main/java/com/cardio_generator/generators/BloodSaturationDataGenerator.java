package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Overview: This class is responsible for generating simulated blood saturation data for
 * multiple patients. It maintains the last known saturation values and updates them with small,
 * random fluctuations to simulate real-world changes in patient saturation levels.
 *
 * Usage: This generator is used within a larger system that handles patient data generation.
 * It's particularly useful in simulation environments where patient monitoring systems are tested.
 * Initialize this class with the number of patients, and use the generate method to produce new data
 * points for each patient during simulation runs.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {
    private static final Random random = new Random();
    private int[] lastSaturationValues;

    /**
     * Constructs a new BloodSaturationDataGenerator.
     *
     * @param patientCount The number of patients for which to simulate data. This count determines
     *                     the size of the internal data structure used to track last saturation values.
     */
    public BloodSaturationDataGenerator(int patientCount) {
        lastSaturationValues = new int[patientCount + 1];

        // Initialize with baseline saturation values for each patient
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); // Initializes with a value between 95 and 100
        }
    }

    /**
     * Generates and outputs new saturation data for a specific patient.
     *
     * This method simulates changes in blood saturation by applying a small random variation to the
     * last known value, ensuring it stays within a range of 90 to 100 percent.
     *
     * @param patientId The unique identifier of the patient for whom data is being generated.
     * @param outputStrategy The strategy to use for outputting the generated data. This could be to
     *                       a console, a file, or any other system capable of handling the output.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); // This will print the stack trace to help identify where the error occurred.
        }
    }
}
