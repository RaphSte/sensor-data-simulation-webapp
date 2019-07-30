package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.Noise;
import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import de.sybit.machinesimulator.essentials.Unit;
import de.sybit.machinesimulator.simulationFunction.SimulationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class SimulationSetUpServiceImpl implements SimulationSetUpService {

    @Autowired
    private SimulationEssentialsContainer essentialsContainer;

    @Autowired
    private SimulationService simulationService;

    @Override
    public void setUpUnit(String unitName) {
        Unit unit = new Unit(unitName);
        essentialsContainer.addNewUnit(unitName, unit);
    }

    @Override
    public void setUpNoise(String unitName, String lowerNoiseBound, String upperNoiseBound) {
        Noise noise = new Noise(unitName, parseToDouble(lowerNoiseBound), parseToDouble(upperNoiseBound));
        essentialsContainer.addNoise(unitName, noise);
    }

    @Override
    public void setUpInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period) {
        Interval interval = new Interval(unitName, parseToDouble(lowerIntervalBound), parseToDouble(upperIntervalBound), Integer.parseInt(period));
        essentialsContainer.addIntervalToNewList(unitName, interval);
    }


    @Override
    public void setUpAdditionalInterval(String unitName, String lowerIntervalBound, String upperIntervalBound, String period) {
        Interval interval = new Interval(unitName, parseToDouble(lowerIntervalBound), parseToDouble(upperIntervalBound), Integer.parseInt(period));
        essentialsContainer.addIntervalToExistingList(unitName, interval);
    }


    @Override
    public void setUpSimulationFunction(String unitName, String simulationFunction) {
        SimulationFunction sf = getNewClassInstanceByName(simulationFunction);
        essentialsContainer.addSimulationFunction(unitName, sf);
    }


    @Override
    public void wrapUpSetUp(String key) {
        simulationService.setSimulationStateFor(key, false);
    }

    //Successful setup is achieved, once this method is reached.
    //its possible to fill this with more functionality tho.
    @Override
    public boolean setUpWasSuccessful() {
        boolean setUpSuccessful = true;
        return setUpSuccessful;
    }

    private double parseToDouble(String doubleString) {
        return Double.parseDouble(doubleString);
    }

    private SimulationFunction getNewClassInstanceByName(String name) {
        try {

            name = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1).toLowerCase();
            //There are only a view occurrences of this path within this Project.
            //Moving this string to a constant would be more clean tho.
            Class<?> aClass = Class.forName("de.sybit.machinesimulator.simulationFunction.impl." + name);

            Constructor<?> constructor = aClass.getConstructor();
            return (SimulationFunction) constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
