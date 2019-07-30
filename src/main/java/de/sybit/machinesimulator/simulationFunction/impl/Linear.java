package de.sybit.machinesimulator.simulationFunction.impl;

import de.sybit.machinesimulator.simulate.Simulation;
import de.sybit.machinesimulator.simulationFunction.SimulationFunctionUtils;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import de.sybit.machinesimulator.essentials.Interval;

import java.time.Duration;
import java.time.LocalDateTime;

public class Linear implements SimulationFunction {

    private LocalDateTime startingPoint;

    private double pitch;

    @Override
    public double calculateSetpoint(Simulation simulation) {

        Interval interval = simulation.getCurrentInterval();

        if (simulation.functionNeedsSetUp()) {
            setUpFunktion(simulation);
        }

        SimulationFunctionUtils su = new SimulationFunctionUtils();

        return (pitch * su.calculateDifferenceInMillis(startingPoint, LocalDateTime.now()) / 1000) + interval.getStartingValue();

    }

    @Override
    public boolean hasReachedExtremePoint(Simulation simulation) {
        long currentDuration = Duration.between(startingPoint, LocalDateTime.now()).getSeconds();
        return currentDuration >= simulation.getCurrentInterval().getTimeFrame() / 2;
    }

    @Override
    public boolean isDescending() {
        return pitch < 0;
    }

    @Override
    public boolean isAscending() {
        return !isDescending();
    }

    @Override
    public LocalDateTime getPeriodStartingTime() {
        return startingPoint;
    }

    private void setUpFunktion(Simulation simulation) {
        startingPoint = LocalDateTime.now();
        pitch = calculatePitch(simulation.getCurrentInterval());
    }

    private double calculatePitch(Interval interval) {
        double halftime = new SimulationFunctionUtils().calculateDifferenceInSeconds(startingPoint,
            startingPoint.plusSeconds(interval.getTimeFrame() / 2));
        double peak = Math.abs(interval.getTargetValue()) - Math.abs(interval.getStartingValue());
        return peak / halftime;
    }

}
