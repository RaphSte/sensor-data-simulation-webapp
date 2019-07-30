package de.sybit.machinesimulator.simulate;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.Noise;
import de.sybit.machinesimulator.essentials.Unit;
import de.sybit.machinesimulator.service.SimulationService;
import de.sybit.machinesimulator.service.TransitionService;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import de.sybit.machinesimulator.simulate.transition.Transition;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Simulation extends Thread {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Simulation.class);

    private SimulationUtils utils = new SimulationUtils();

    private Transition transition;

    private Unit unit;

    private Noise noise;

    private List<Interval> intervals;

    private int intervalIndex = 0;

    private SimulationFunction simulationFunction;

    private boolean setUpFunction = true;

    private boolean setUpFunctionWithoutMultipleIntervals = true;


    //Wired in manually, using constructor
    private SimulationService simulationService;

    //Wired in manually, using constructor
    private TransitionService transitionService;


    @Override
    public void run() {
        utils.createFile();
        simulate();

    }

    private void simulate() {
        LOG.info("Simulation for '" + unit.getKind() + "' started");
        int i = 0;
        while (simulationService.isSimulationRunning(unit.getKind())) {

            i++;

            if (simulationService.isErrorStateFor(unit.getKind())) {
                if (transition == null) {
                    transition = utils.getMatchingTransition(simulationFunction);
                }
                transition.processTransitionFor(this);
            }

            double value = simulationFunction.calculateSetpoint(this);
            setUpFunction = false;
            setUpFunctionWithoutMultipleIntervals = false;

            //depends on kind of simulation
            if (simulationFunction.hasReachedExtremePoint(this)) {
                applyNewInterval();
            }

            value = applyNoise(value);
            refreshValue(value);



            try {
                Simulation.sleep(SimulationConstants.SIMULATION_TIME_OFFSET);
                utils.writeToFile(i, value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Simulation for '" + unit.getKind() + "' stopped");
    }

    public void applyNewInterval() {
        setUpFunction = true;
        if (intervalIndex >= intervals.size() - 1) {
            intervalIndex = 0;
        } else {
            intervalIndex++;
        }
    }

    public boolean valueReachedExtremePoint() {
        return simulationFunction.hasReachedExtremePoint(this);
    }

    public boolean functionNeedsSetUp() {
        return setUpFunction;
    }

    public boolean functionWithoutMultipleIntervalsNeedsSetUp() {
        return setUpFunctionWithoutMultipleIntervals;
    }

    public Interval getCurrentInterval() {
        return intervals.get(intervalIndex);
    }

    public void resetIntervalIndex() {
        intervalIndex = 0;
    }

    private double applyNoise(double value) {
        return value + noise.getNoise();
    }

    private void refreshValue(double value) {
        unit.setValue(value);
    }

    public void setErrorStateFor(String key, boolean errorState) {
        simulationService.setErrorStateFor(key, errorState);
    }

    public Simulation(Unit unit, Noise noise, List<Interval> intervals, SimulationFunction simulationFunction, SimulationService simulationService, TransitionService transitionService) {
        this.unit = unit;
        this.noise = noise;
        this.intervals = intervals;
        this.simulationFunction = simulationFunction;
        this.simulationService = simulationService;
        this.transitionService = transitionService;
    }

    public Simulation() {
        //default constructor
    }

    public Unit getUnit() {
        return unit;
    }

    public SimulationFunction getSimulationFunction() {
        return simulationFunction;
    }

    public TransitionService getTransitionService() {
        return transitionService;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        LOG.debug("Intervals where set: " + intervals.get(0).getTimeFrame() + " seconds");
    }

    public void setSetUpFunction(boolean setUpFunction) {
        this.setUpFunction = setUpFunction;
    }

    public void setSetUpFunctionWithoutMultipleIntervals(boolean setUpFunction) {
        this.setUpFunctionWithoutMultipleIntervals = setUpFunction;
    }

}
