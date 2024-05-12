package com.alerts;

import com.cardio_generator.generators.ECGDataGenerator;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Comparator.comparing;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private Map<Integer, ECGDataGenerator> ecgDataGeneratorMap;
    private static final Logger LOGGER = Logger.getLogger(AlertGenerator.class.getName());

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        long currentTime = System.currentTimeMillis();
        long tenMinutes = currentTime - (10 * 60 * 1000);
        List<PatientRecord> saturationRecord = dataStorage.getRecords(patient.getPatientId(), tenMinutes, currentTime);

        evaluateSaturationData(patient, saturationRecord);
        checkHypotensiveHypoxemia(patient);

        ECGDataGenerator ecgDataGenerator = this.ecgDataGeneratorMap.get(patient.getPatientId());
        if (ecgDataGenerator == null) {
            detectAbnormalRate(patient, ecgDataGenerator);
        }
    }

    private void evaluateSaturationData(Patient patient, List<PatientRecord> saturationRecord) {
        long currentTime = System.currentTimeMillis();

        // Check if there are at least 3 saturation records to compare
        if (saturationRecord.size() < 3) {
            return;
        }

        PatientRecord latest = saturationRecord.get(saturationRecord.size() - 1);
        PatientRecord tenMinutesRecord = saturationRecord.get(saturationRecord.size() - 3);

        if (latest.getMeasurementValue() < 92) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Low Saturation Detected", currentTime));
        } else if (latest.getMeasurementValue() - tenMinutesRecord.getMeasurementValue() >= 5) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Rapid Drop in Blood Saturation Alert", currentTime));
        }
    }

    public void detectAbnormalRate(Patient patient, ECGDataGenerator ecgDataGenerator) {
        long currentTime = System.currentTimeMillis();
        ArrayList<Double> hbIntervals = ecgDataGenerator.getHeartBeatIntervals(patient.getPatientId());
        double heartRate = ecgDataGenerator.getLastHeartRate(patient.getPatientId());

        double average = hbIntervals.stream().mapToDouble(val -> val).average().orElse(0.0);

        double sd = Math.sqrt(hbIntervals.stream().mapToDouble(val -> Math.pow(val - average, 2)).average().orElse(0.0));


        if (heartRate < 50 || heartRate > 100) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Abnormal Heart Rate Alert", currentTime));
        } else if (sd > 0.1) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Irregular Heart Rate Alert", currentTime));
        }
    }


    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        LOGGER.info(alert.toString());
        // Implementation might involve logging the alert or notifying staff
    }

    public void trendAlert(List<PatientRecord> trendRecord) {
        // Sort the records by their timestamp, from lowest to highest
        trendRecord.sort(Comparator.comparing(PatientRecord::getTimestamp));
        // Verify that the trend of the records we are looking at is only 
        for (int i = 2; i < trendRecord.size(); i++) {
            if ("SystolicPressure".equals(trendRecord.get(i).getRecordType()) || "DiastolicPressure".equals(trendRecord.get(i).getRecordType())) {

                // Calculate the differences between the last 3 readings made
                double alteration1 = trendRecord.get(i - 2).getMeasurementValue() - trendRecord.get(i - 1).getMeasurementValue();
                double alteration2 = trendRecord.get(i - 1).getMeasurementValue() - trendRecord.get(i).getMeasurementValue();

                // Check if the alterations are greater than 10 and have the same sign
                if (Math.abs(alteration1) > 10 && Math.abs(alteration2) > 10 && Math.signum(alteration1) == Math.signum(alteration2)) {
                    // Generate an alert message for the patient
                    String alertType = alteration1 > 0 ? "Blood Pressure Increasing Trend Alert" : "Blood Pressure Decreasing Trend Alert";

                    // Triggers an alert for the monitoring system
                    Alert alert = new Alert(String.valueOf(trendRecord.get(i).getPatientId()), alertType, trendRecord.get(i).getTimestamp());
                }
            }
        }
    }

    public void triggerManualAlert(int patientId) {
        long currentTime = System.currentTimeMillis();
        triggerAlert(new Alert(Integer.toString(patientId), "Alert Button Pressed", currentTime));
    }

    private void checkHypotensiveHypoxemia(Patient patient) {
        long currentTime = System.currentTimeMillis();
        long tenMinutes = currentTime - (10 * 60 * 1000);
        // Get the patient's systolic pressure and saturation records for the last 10 minutes

        List<PatientRecord> systolicPressureRecord = dataStorage.getRecords(patient.getPatientId(), tenMinutes, currentTime);
        List<PatientRecord> saturationRecord = dataStorage.getRecords(patient.getPatientId(), tenMinutes, currentTime);

        if (!systolicPressureRecord.isEmpty() && !saturationRecord.isEmpty()) {
            PatientRecord latestSystolicPressure = systolicPressureRecord.get(systolicPressureRecord.size() - 1);
            PatientRecord latestSaturation = saturationRecord.get(saturationRecord.size() - 1);

            if (latestSystolicPressure.getMeasurementValue() < 90.0 && latestSaturation.getMeasurementValue() < 92.0) {
                triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Hypotensive Hypoxemia Alert", currentTime));
            }
        }
    }
}


