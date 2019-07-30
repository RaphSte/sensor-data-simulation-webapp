package de.sybit.machinesimulator.simulate.transition.impl;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.Unit;
import de.sybit.machinesimulator.service.TransitionService;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import de.sybit.machinesimulator.simulate.Simulation;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import de.sybit.machinesimulator.simulationFunction.SimulationFunctionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class LinearTransition implements de.sybit.machinesimulator.simulate.transition.Transition {

    private static final Logger log = Logger.getLogger(LinearTransition.class.getName());

    private boolean transitionFinished = false;

    private boolean transitionStarted = false;

    private boolean isAscendingTransition = false;

    private boolean isDescentdingTransition = false;

    @Override
    public void processTransitionFor(Simulation simulation) {
        Unit unit = simulation.getUnit();
        evaluateTransition(simulation);
        String kind = unit.getKind();
        TransitionService transitionService = simulation.getTransitionService();

        if (!transitionStarted) {
            log.info("transition for \"" + kind + "\" in progress");
            transitionStarted = true;
            simulation.setSetUpFunction(true);
            simulation.resetIntervalIndex();

            Interval interval = modifyIntervalForTransition(unit, transitionService.provideTransitionIntervalsFor(kind).get(0),
                    simulation.getCurrentInterval(), simulation.getSimulationFunction());

            simulation.setIntervals(Arrays.asList(interval));
        }

        if (transitionStarted && transitionFinished) {
            simulation.resetIntervalIndex();
            simulation.setIntervals(modifyIntervalForTransitionWrapUp(transitionService.provideTransitionIntervalsFor(kind)));
            simulation.applyNewInterval();
            simulation.setErrorStateFor(kind, false);
            log.info("transition for \"" + kind + "\" is finished");
        }

    }

    private void evaluateTransition(Simulation simulation) {
        if (simulation.getSimulationFunction().hasReachedExtremePoint(simulation)) {
            transitionFinished = true;
        }
    }

    private Interval modifyIntervalForTransition(Unit unit, Interval interval, Interval currentInterval, SimulationFunction linear) {
        double value = unit.getValue();

        double processDegree = processDegree(linear, currentInterval);
        int timeProcessed = Math.round((float) (interval.getTimeFrame() * processDegree));
        int newTimeFrame = currentInterval.getTimeFrame() - timeProcessed;

        if (linear.isAscending()) {
            isAscendingTransition = true;
            return new Interval(interval.getKind(), value, interval.getTargetValue(), newTimeFrame);
        } else {
            isDescentdingTransition = true;
            return new Interval(interval.getKind(), value, interval.getStartingValue(), newTimeFrame);
        }
    }

    private List<Interval> modifyIntervalForTransitionWrapUp(List<Interval> intervals) {
        if (isDescentdingTransition) {
            return Arrays.asList(intervals.get(1), intervals.get(0));
        } else {
            return intervals;
        }
    }

    private double processDegree(SimulationFunction linear, Interval currentInterval) {
        float exactPassedTime =
                new SimulationFunctionUtils().calculateDifferenceInMillis(linear.getPeriodStartingTime(), LocalDateTime.now())
                        / SimulationConstants.MILLISECONDS_FACTOR;
        return exactPassedTime / (currentInterval.getTimeFrame() / 2);
    }

}
