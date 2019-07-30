package de.sybit.machinesimulator.service;

import java.util.List;

public interface ObjectNameService {


    List<String> provideSimulationTypes();

    List<String> provideAvailableSimulations();
}
