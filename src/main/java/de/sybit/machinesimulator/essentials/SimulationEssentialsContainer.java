package de.sybit.machinesimulator.essentials;

import de.sybit.machinesimulator.simulate.Simulation;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.*;

@Service
public class SimulationEssentialsContainer {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SimulationEssentialsContainer.class);

    private Map<String, Object[]> container = new HashMap<>();

    public void addNewUnit(String id, Unit unit) {
        container.put(id, new Object[6]);
        addToContainerAt(id, unit, 0);
    }

    public void addNoise(String id, Noise noise) {
        addToContainerAt(id, noise, 1);
    }

    public void addIntervalToNewList(String id, Interval interval) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(interval);
        addToContainerAt(id, intervals, 2);
    }

    public void addIntervalToExistingList(String id, Interval interval) {
        List<Interval> list = (List) container.get(id)[2];
        list.add(interval);
        addToContainerAt(id, list, 2);
    }

    public void addSimulationFunction(String id, SimulationFunction simulationFunction) {
        addToContainerAt(id, simulationFunction, 3);
    }

    public void addSimulation(String id, Simulation simulation) {
        addToContainerAt(id, simulation, 4);
    }

    public void addTransitionIntervalToNewList(String id, Interval interval) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(interval);
        addToContainerAt(id, intervals, 5);
    }

    public void addTransitionIntervalToExistingList(String id, Interval interval) {
        List<Interval> list = (List) container.get(id)[5];
        list.add(interval);
        addToContainerAt(id, list, 5);
    }

    public boolean transitionIntervalListExistsFor(String id) {
        if (!container.containsKey(id)) {
            return false;
        }
        List transitionList = (List) container.get(id)[5];
        return transitionList != null;
    }

    public Set<String> getKeySet() {
        return container.keySet();
    }

    public Unit getUnitFor(String id) {
        if (container.containsKey(id)) {
            return (Unit) container.get(id)[0];
        } else {
            //intentional null
            return null;
        }
    }

    public Noise getNoiseFor(String id) {
        return (Noise) container.get(id)[1];
    }

    public List<Interval> getIntervalsFor(String id) {
        return (List) container.get(id)[2];
    }

    public SimulationFunction getSimulationFunctionFor(String id) {
        return (SimulationFunction) container.get(id)[3];
    }

    public Simulation getSimulationFor(String id) {
        return (Simulation) container.get(id)[4];
    }

    public List<Interval> getTransitionIntervalsFor(String id) throws NotFoundException {
        if (transitionIntervalListExistsFor(id)) {
            return (List) container.get(id)[5];
        }
        LOG.error("Could not find TransitionIntervalsFor");
        throw new NotFoundException("Could not find TransitionIntervalsFor");
    }

    public void dropAllFor(String key) {
        container.remove(key);
    }


    /**
     * @param index 0 = Unit; 1 = Noise; 2 = IntervalList; 3 = SimulationFunction; 4 = Simulation 5 = TransitionIntervals
     */
    private void addToContainerAt(String key, Object obj, int index) {
        Object[] objects = container.get(key);
        objects[index] = obj;
        container.put(key, objects);
    }

}
