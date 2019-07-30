package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import de.sybit.machinesimulator.essentials.Unit;
import de.sybit.machinesimulator.simulate.Simulation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class SimulationServiceImpl implements SimulationService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SimulationService.class);

    @Autowired
    private SimulationEssentialsContainer ec;

    @Autowired
    private TransitionService transitionService;

    private Map<String, Boolean> errorStates = new HashMap<>();

    private Map<String, Boolean> simulationRunningState = new HashMap<>();

    @Override
    public void setErrorStateFor(String key, boolean errorState) {
        errorStates.put(key, errorState);
    }

    @Override
    public boolean isErrorStateFor(String key) {
        if (errorStates.get(key) == null) {
            return false;
        }
        return errorStates.get(key);
    }

    @Override
    public void setSimulationStateFor(String key, boolean runningState) {
        simulationRunningState.put(key, runningState);
    }

    @Override
    public boolean isSimulationRunning(String key) {
        if (simulationRunningState.get(key) == null) {
            return false;
        }
        return simulationRunningState.get(key);
    }


    @Override
    public void StartNewSimulationFor(String unitName) {
        Simulation simulation = createNewSimulationFor(unitName);
        ec.addSimulation(unitName, simulation);
        simulation.start();

        //left in for debugging purposes
        //new Thread(() -> print(unitName)).start();
    }

    private void print(String unitName) {
        for (int i = 0; i <= 1000; i++) {
            try {
                new Thread().sleep(250);
                System.out.println("Asynchronus|" + i + "|\t" + ec.getUnitFor(unitName).getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public double getValueFor(String unitName) {
        Unit unit = ec.getUnitFor(unitName);

        if (unit == null) {
            LOG.error("No unit by the name of '" + unitName + "'. returning 0 as fallback");
            return 0;
        }

        return unit.getValue();
    }

    @Override
    public double getValueFor(String unitName, int decimalPlaces) {
        NumberFormat nf = DecimalFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(decimalPlaces);
        return Double.parseDouble(nf.format(getValueFor(unitName)));
    }

    //instance of simulation service given for manual dependency mngment
    private Simulation createNewSimulationFor(String unitName) {
        return new Simulation(ec.getUnitFor(unitName), ec.getNoiseFor(unitName), ec.getIntervalsFor(unitName),
                ec.getSimulationFunctionFor(unitName), this, transitionService);
    }

}
