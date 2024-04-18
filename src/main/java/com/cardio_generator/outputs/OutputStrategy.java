package com.cardio_generator.outputs;

/**
 * The OutputStrategy interface is used for sending out patient data in various ways.
 * It allows different parts of our system to output data consistently, even if the
 * underlying methods vary (e.g., displaying on screen, saving to files, or sending
 * over networks).
 */
public interface OutputStrategy {

    /**
     * Sends out data about a patient.
     *
     * Implement this method to handle the specifics of how data is sent out.
     * This could mean formatting the data appropriately and ensuring it reaches
     * its intended destination, like a database, a log file, or another data sink.
     *
     * @param patientId The ID of the patient, used to track whose data it is.
     * @param timestamp The time when the data was recorded, given in milliseconds
     *                  since January 1, 1970 (Unix epoch).
     * @param label A label describing the type of data, such as 'heart rate'.
     * @param data The actual data being sent, formatted as a string.
     */
    void output(int patientId, long timestamp, String label, String data);
}
