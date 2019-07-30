package de.sybit.machinesimulator.service;

public interface SimulationService {

    /**
     * @param runningState If true, simulation is enabled. If false, simulation is disabled.
     */
    void setSimulationStateFor(String key, boolean runningState);

    boolean isSimulationRunning(String key);

    void StartNewSimulationFor(String unitName);

    void setErrorStateFor(String key, boolean errorState);

    boolean isErrorStateFor(String key);

    double getValueFor(String unitName);

    double getValueFor(String unitName, int decimalPlaces);

}
