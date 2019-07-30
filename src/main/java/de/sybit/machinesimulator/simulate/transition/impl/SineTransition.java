package de.sybit.machinesimulator.simulate.transition.impl;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.Unit;
import de.sybit.machinesimulator.service.TransitionService;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import de.sybit.machinesimulator.simulate.Simulation;
import de.sybit.machinesimulator.simulate.transition.Transition;
import de.sybit.machinesimulator.simulationFunction.impl.Sine;

import java.util.Arrays;
import java.util.logging.Logger;

public class SineTransition implements Transition {

    private static final Logger LOG = Logger.getLogger(SineTransition.class.getName());

    private boolean transitionFinished = false;

    private boolean transitionStarted = false;

    @Override
    public void processTransitionFor(Simulation simulation) {
        Unit unit = simulation.getUnit();
        evaluateTransition(simulation);
        String kind = unit.getKind();

        if (!transitionStarted) {
            LOG.info("transition for \"" + kind + "\" in progress");
            transitionStarted = true;
            simulation.setSetUpFunctionWithoutMultipleIntervals(true);
            Interval interval = getNewInterval(simulation, unit);
            Interval modifiedInterval = modifyIntervalForTransition(unit, interval);
            simulation.setIntervals(Arrays.asList(modifiedInterval));
        }

        if (transitionStarted && transitionFinished) {
            simulation.resetIntervalIndex();
            Interval interval = getNewInterval(simulation, unit);
            Sine sine = (Sine) simulation.getSimulationFunction();
            sine.shiftFunctionHorizontalBy((double) -simulation.getCurrentInterval().getTimeFrame() / 4,
                    SimulationConstants.MILLISECONDS_FACTOR);
            simulation.setIntervals(Arrays.asList(interval));
            simulation.setSetUpFunctionWithoutMultipleIntervals(true);
            simulation.setErrorStateFor(kind, false);
            sine.setTransitionIsDone(true);
            LOG.info("transition for \"" + kind + "\" is finished");
        }

    }

    private Interval getNewInterval(Simulation simulation, Unit unit) {
        String unitNameKey = simulation.getUnit().getKind();
        Interval interval;
        TransitionService transitionService = simulation.getTransitionService();

        if (transitionService.newTransitionIntervalsAvailableFor(unitNameKey)) {
            // in case of sine, there is only 1 interval to be used.
            interval = transitionService.provideTransitionIntervalsFor(unitNameKey).get(0);
        } else {
            interval = simulation.getCurrentInterval();
        }
        return interval;
    }

    private Interval modifyIntervalForTransition(Unit unit, Interval interval) {
        double value = unit.getValue();
        double targetValue = interval.getTargetValue();
        return new Interval(interval.getKind(), value - differenceBetween(value, targetValue), targetValue, interval.getTimeFrame());
    }

    private double differenceBetween(double currentValue, double targetValue) {
        return Math.abs(currentValue - targetValue);
    }

    private void evaluateTransition(Simulation simulation) {
        if (transitionStarted && simulation.valueReachedExtremePoint()) {
            // simulation.resetStartingTime();
            transitionFinished = true;
        }
    }

}
