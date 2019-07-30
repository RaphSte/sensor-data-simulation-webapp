package de.sybit.machinesimulator.simulationFunction;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimulationFunctionUtils {

    public long calculateDifferenceInMillis(LocalDateTime timeA, LocalDateTime timeB) {
        return Duration.between(timeA, timeB).toMillis();
    }

    public long calculateDifferenceInSeconds(LocalDateTime timeA, LocalDateTime timeB) {
        return calculateDifferenceInMillis(timeA, timeB) / 1000;
    }





}
