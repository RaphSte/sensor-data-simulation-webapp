package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.entities.SimulationValues;
import de.sybit.machinesimulator.exception.ResourceNotFoundException;
import de.sybit.machinesimulator.repository.SimulationValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SimulationValuesServiceImpl implements SimulationValuesService {


    @Autowired
    SimulationValuesRepository simulationValuesRepository;

    @Override
    public SimulationValues findById(long id) throws ResourceNotFoundException {
        return simulationValuesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SimulationValues", "id", Long.toString(id)));
    }


    @Override
    public SimulationValues findByNameKey(String nameKey) throws ResourceNotFoundException {
        return simulationValuesRepository.getSimulationValuesByUnit_name_key(nameKey).orElseThrow(() -> new ResourceNotFoundException("SimulationValues", "nameKey", nameKey));
    }

    @Override
    public SimulationValues updateSimulationValues(SimulationValues simulationValues) {
        simulationValues.setUpdated(new Date());
        return simulationValuesRepository.save(simulationValues);
    }


    @Override
    public SimulationValues saveNewSimulationValues(SimulationValues simulationValues) {
        Date date = new Date();
        simulationValues.setCreated(date);
        simulationValues.setUpdated(date);
        return simulationValuesRepository.save(simulationValues);
    }

    @Override
    public List<SimulationValues> findAll() {
        return simulationValuesRepository.findAll();
    }

    @Override
    public void deleteEntry(SimulationValues simulationValues) {
        simulationValuesRepository.delete(simulationValues);
    }


    @Override
    public List<String> findAllNameKeys() {
        List<String> allNames = new ArrayList<>();
        List<SimulationValues> allValuesContainer = simulationValuesRepository.findAll();
        for (SimulationValues values : allValuesContainer) {
            allNames.add(values.getUnit_name_key());
        }
        return allNames;
    }


}
