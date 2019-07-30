package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.simulationFunction.TransitionIntervalProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransitionSetUpServiceImpl implements TransitionSetUpService {


    private boolean wasSuccessful = true;


    @Autowired
    TransitionService transitionService;

    @Override
    public void setUpInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period) {
        Interval interval =
                new Interval(unitName, parseToDouble(lowerIntervalBound), parseToDouble(upperIntervalBound), Integer.parseInt(period));

        TransitionIntervalProviderService.addInterval(interval);
        transitionService.addTransitionInterval(interval);
    }

    @Override
    public void setUpAdditionalInterval(String unitNameKey, String lowerIntervalBound, String upperIntervalBound, String period) {
        Interval interval =
                new Interval(unitNameKey, parseToDouble(lowerIntervalBound), parseToDouble(upperIntervalBound), Integer.parseInt(period));

        transitionService.addTransitionInterval(interval);
        TransitionIntervalProviderService.addInterval(interval);
    }

    @Override
    public boolean setUpWasSuccessful() {
        return wasSuccessful;
    }

    private double parseToDouble(String doubleString) {
        return Double.parseDouble(doubleString);
    }

}

