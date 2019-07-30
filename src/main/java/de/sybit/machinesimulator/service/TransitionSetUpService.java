package de.sybit.machinesimulator.service;

public interface TransitionSetUpService {


    public void setUpInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period);

    public void setUpAdditionalInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period);

    public boolean setUpWasSuccessful();

}
