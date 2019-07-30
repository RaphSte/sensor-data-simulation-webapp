package de.sybit.machinesimulator.simulate;

import de.sybit.machinesimulator.setUp.SimulationConstants;
import de.sybit.machinesimulator.simulate.transition.Transition;
import de.sybit.machinesimulator.simulate.transition.impl.LinearTransition;
import de.sybit.machinesimulator.simulate.transition.impl.SineTransition;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import de.sybit.machinesimulator.simulationFunction.impl.Linear;
import de.sybit.machinesimulator.simulationFunction.impl.Sine;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimulationUtils {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SimulationUtils.class);

    private static final String DATASET_LOGGING_PATH = "../logs/Datasets";

    private int datasetNumber;

    private final String startingDateString;

    private static boolean errorState;

    public static boolean isErrorState() {
        return errorState;
    }

    public static void setErrorState(boolean errorState) {
        SimulationUtils.errorState = errorState;
    }


    public SimulationUtils() {
        SimpleDateFormat date = new SimpleDateFormat("MM_dd-HH_mm_ss");
        startingDateString = date.format(new Date());
    }

    public void createFile() {
        createDatasetDirectoryIfNotExisting();
        datasetNumber = getDatasetNumber(datasetNumber);
        String filename = DATASET_LOGGING_PATH + "/" + datasetNumber + "-" + startingDateString + ".csv";
        File f = new File(filename);

        try {
            f.createNewFile();
            LOG.info("Created file '" + f + "' successfully");
        } catch (IOException e) {
            LOG.error("Failed to create file'" + f + "'");
        }
    }

    public void writeToFile(int i, double result) {
        String filename = DATASET_LOGGING_PATH + "/" + datasetNumber + "-" + startingDateString + ".csv";
        Path file = Paths.get(filename);

        NumberFormat nf = DecimalFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(SimulationConstants.MAXIMUM_FRACTION_DIGITS);

        List<String> lines = Arrays.asList(i + SimulationConstants.CSV_LINE_SEPERATOR + (nf.format(result)));
        try {
            Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOG.error("Failed to write to file!");
        }
    }

    private void createDatasetDirectoryIfNotExisting() {
        File datasetLoggingDir = new File(DATASET_LOGGING_PATH);
        if (!datasetLoggingDir.isDirectory()) {
            datasetLoggingDir.mkdir();
        }
    }


    public Transition getMatchingTransition(SimulationFunction simulationFunction) {

        Class simulationClass = simulationFunction.getClass();
        if (simulationClass == Linear.class) {
            return new LinearTransition();
        }
        if (simulationClass == Sine.class) {
            return new SineTransition();
        }
        return null;
    }

    private int getDatasetNumber(int datasetNumber) {
        String path = DATASET_LOGGING_PATH + "/" + (datasetNumber + 1) + "-" + startingDateString + ".csv";
        Path file = Paths.get(path);

        if (!file.toFile().exists()) {
            return datasetNumber + 1;
        } else {
            return getDatasetNumber(datasetNumber + 1);
        }
    }

}
