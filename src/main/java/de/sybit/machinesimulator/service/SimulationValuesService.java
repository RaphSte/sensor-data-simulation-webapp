package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.entities.SimulationValues;
import de.sybit.machinesimulator.exception.ResourceNotFoundException;

import java.util.List;

public interface SimulationValuesService {
    SimulationValues findById(long id) throws ResourceNotFoundException;

    SimulationValues findByNameKey(String nameKey) throws ResourceNotFoundException;

    SimulationValues updateSimulationValues(SimulationValues simulationValues);

    SimulationValues saveNewSimulationValues(SimulationValues simulationValues);

    List<SimulationValues> findAll();

    void deleteEntry(SimulationValues simulationValues);

    List<String> findAllNameKeys();
}
