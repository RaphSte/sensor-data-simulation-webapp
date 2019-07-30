package de.sybit.machinesimulator.simulationFunction;

import de.sybit.machinesimulator.simulate.Simulation;

import java.time.LocalDateTime;

public interface SimulationFunction {

    double calculateSetpoint(Simulation simulation);

    boolean hasReachedExtremePoint(Simulation simulation);

    boolean isDescending();

    boolean isAscending();

    LocalDateTime getPeriodStartingTime();

}
