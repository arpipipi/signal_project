package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Overview: Responsible for generating alert conditions for patients. It uses a
 * probabilistic approach to either trigger new alerts or resolve existing ones, simulating
 * a dynamic alert environment typical in patient monitoring systems.
 *
 * Usage: Utilize this class within healthcare simulation systems where monitoring
 * of patient alerts is critical. It's particularly useful in systems testing alert mechanisms
 * and response efficacy. Initialize with the number of patients, and use the generate method
 * to manage alert states during simulation cycles.
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    private final boolean[] alertStates; // false = resolved, true = pressed
    // Renamed variable AlertStates to LowerCamelCase
    // Made alertStates final to prevent modification

    /**
     * Constructs a new AlertGenerator.
     *
     * @param patientCount The number of patients for which to manage alerts. This count
     *                     determines the size of the array used to track alert states.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; // Renamed AlertStates to LowerCamelCase
    }

    /**
     * Generates and outputs alert data for a specific patient based on probabilistic calculations.
     * Alerts can either be triggered or resolved. This method updates the alert state and outputs
     * the current state for each patient.
     *
     * @param patientId The unique identifier of the patient for whom the alert state is being generated.
     * @param outputStrategy The strategy to use for outputting the generated data. This could be to
     *                       a console, a file, or any other system capable of handling the output.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) { // Renamed AlertStates to LowerCamelCase
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false; // Renamed AlertStates to LowerCamelCase
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double Lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-Lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true; // Renamed AlertStates to LowerCamelCase
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
