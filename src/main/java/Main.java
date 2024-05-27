import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;
import java.io.IOException;

public class Main {
    /**
     * The main entry point for the application.
     *
     * @param args command line arguments
     * @throws IOException if there is an I/O error
     */
    public static void main(String[] args) throws IOException {
        // Check if there are any command line arguments
        if (args.length > 0 && args[0].equals("DataStorage")) {
            // If the first argument is "DataStorage", run the main method of DataStorage
            DataStorage.main(new String[]{});
        } else {
            // Otherwise, run the main method of HealthDataSimulator
            HealthDataSimulator.main(new String[]{});
        }
    }
}
