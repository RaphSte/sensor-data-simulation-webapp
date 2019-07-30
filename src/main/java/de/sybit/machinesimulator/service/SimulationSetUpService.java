package de.sybit.machinesimulator.service;

public interface SimulationSetUpService {

    public void setUpUnit(String unitName);

    public void setUpNoise(String unitName, String lowerNoiseBound, String upperNoiseBound);

    public void setUpInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period);

    void setUpAdditionalInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period);

    public void setUpSimulationFunction(String unitName, String simulationFunction);

    void wrapUpSetUp(String key);

    public boolean setUpWasSuccessful();

}
