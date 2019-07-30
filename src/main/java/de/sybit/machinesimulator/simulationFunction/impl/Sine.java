package de.sybit.machinesimulator.simulationFunction.impl;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import de.sybit.machinesimulator.simulate.Simulation;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import de.sybit.machinesimulator.simulationFunction.SimulationFunctionUtils;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Sine implements SimulationFunction {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Sine.class);


    private double compressionX = 1;

    private double compressionY = 1;

    private double shiftY;

    private double shiftX;

    private LocalDateTime startingPoint;

    private boolean isFirstPeriod;

    private boolean transitionIsDone;

    private boolean isDescending;

    private int simulationStep;

    private int quaterIntervalStep;

    public void shiftFunctionHorizontalBy(double value, int timeFactor) {
        shiftX = value * timeFactor;
        LOG.debug("shift by: " + shiftX);
    }

    @Override
    public double calculateSetpoint(Simulation simulation) {
        simulationStep++;

        if (simulation.functionWithoutMultipleIntervalsNeedsSetUp()) {
            setUpFunction(simulation.getCurrentInterval());
        }

        if (hasReachedExtremePoint(simulation)) {
            LOG.debug("sine reached xtremepoint");
            isDescending = !isDescending;
        }

        SimulationFunctionUtils su = new SimulationFunctionUtils();
        double innerSin = compressionX * (su.calculateDifferenceInMillis(startingPoint, LocalDateTime.now()) - shiftX)
            / SimulationConstants.MILLISECONDS_FACTOR;

        if (transitionIsDone) {
            transitionIsDone = false;
            innerSin = compressionX * (0 - shiftX) / SimulationConstants.MILLISECONDS_FACTOR;
        }

        return compressionY * Math.sin(innerSin) + shiftY;
    }

    @Override
    public boolean isDescending() {
        return isDescending;
    }

    @Override
    public boolean isAscending() {
        return !isDescending();
    }

    @Override
    public boolean hasReachedExtremePoint(Simulation simulation) {
        Interval interval = simulation.getCurrentInterval();
        return simulationStep == quaterIntervalStep || simulationStep == quaterIntervalStep * 3;
    }

    @Override
    public LocalDateTime getPeriodStartingTime() {
        return startingPoint;
    }

    private void setUpFunction(Interval interval) {
        compressionX = calculateCompressionX(interval.getTimeFrame());
        compressionY = calculateCompressionY(interval);
        shiftY = calculateShiftY(interval);
        LOG.debug("Reset simulationStep");
        simulationStep = 0;
        quaterIntervalStep = calculateQuaterIntervalSteps(interval);
        startingPoint = LocalDateTime.now();
    }

    private int calculateQuaterIntervalSteps(Interval interval) {
        return Math
            .round((interval.getTimeFrame()) * SimulationConstants.MILLISECONDS_FACTOR / SimulationConstants.SIMULATION_TIME_OFFSET / 4);
    }

    private double calculateShiftY(Interval interval) {
        return interval.getStartingValue() + compressionY;
    }

    private double calculateCompressionY(Interval interval) {
        return Math.abs(interval.getTargetValue() - interval.getStartingValue()) / 2;
    }

    private double calculateCompressionX(double period) {
        return 2 * Math.PI / period;
    }

    public boolean isTransitionIsDone() {
        return transitionIsDone;
    }

    public void setTransitionIsDone(boolean transitionIsDone) {
        this.transitionIsDone = transitionIsDone;
    }
}
