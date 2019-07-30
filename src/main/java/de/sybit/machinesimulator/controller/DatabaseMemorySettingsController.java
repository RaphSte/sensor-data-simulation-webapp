package de.sybit.machinesimulator.controller;

import de.sybit.machinesimulator.entities.SimulationValues;
import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import de.sybit.machinesimulator.exception.ResourceNotFoundException;
import de.sybit.machinesimulator.service.ObjectNameService;
import de.sybit.machinesimulator.service.SimulationService;
import de.sybit.machinesimulator.service.SimulationSetUpService;
import de.sybit.machinesimulator.service.SimulationValuesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class DatabaseMemorySettingsController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DatabaseMemorySettingsController.class);

    @Autowired
    SimulationValuesService simulationValuesService;

    @Autowired
    SimulationSetUpService simulationSetUpService;

    @Autowired
    SimulationService simulationService;

    @Autowired
    ObjectNameService nameService;

    @Autowired
    SimulationEssentialsContainer ec;


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_LOAD_FROM_DATABASE, method = RequestMethod.GET)
    public String loadFromDatabase(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {


        fillModel(model);

        return ControllerConstants.PAGE_LOAD_FROM_DATABASE;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_LOAD_FROM_DATABASE, method = RequestMethod.POST)
    public String loadFromDatabase(Map<String, Object> model, @RequestParam String unitNameKey, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {

        SimulationValues sv = null;

        try {
            sv = simulationValuesService.findByNameKey(unitNameKey);
            simulationSetUpService.setUpUnit(unitNameKey);
            simulationSetUpService.setUpNoise(unitNameKey, String.valueOf(sv.getNoise_lower_bound()), String.valueOf(sv.getNoise_upper_bound()));
            simulationSetUpService.setUpInterval(unitNameKey, String.valueOf(sv.getInterval_lower_bound()),
                    String.valueOf(sv.getInterval_upper_bound()), String.valueOf(sv.getInterval_period()));
            simulationSetUpService.setUpSimulationFunction(unitNameKey, sv.getSimulation_function());

            simulationSetUpService.wrapUpSetUp(unitNameKey);


            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Loaded simulation successfully!");
        } catch (ResourceNotFoundException e) {
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Loading failed! Simulation does not exist.");
            LOG.error("Error trying to load simulation. Simulation does not exist in database.");
        }

        fillModel(model);

        return ControllerConstants.PAGE_REDIRECT_LOAD_FROM_DATABASE;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_LOAD_FROM_DATABASE + "/drop", method = RequestMethod.POST)
    public String loadFromDatabaseDrop(Map<String, Object> model, @RequestParam String unitNameKey, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {

        SimulationValues sv = null;

        try {
            sv = simulationValuesService.findByNameKey(unitNameKey);
            simulationValuesService.deleteEntry(sv);
            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Dropped entry successful");
        } catch (ResourceNotFoundException e) {
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Entry does not exist and therefore can not be dropped");
            LOG.error("Error trying to drop entry from database. Entry does not exist and therefore can not be dropped");
        }

        fillModel(model);

        return ControllerConstants.PAGE_REDIRECT_LOAD_FROM_DATABASE;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_LOAD_FROM_DATABASE + "/drop_memory", method = RequestMethod.POST)
    public String dropFromMemory(Map<String, Object> model, @RequestParam String unitNameKey, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {


        ec.dropAllFor(unitNameKey);
        if (simulationService.isSimulationRunning(unitNameKey)) {
            simulationService.setSimulationStateFor(unitNameKey, false);
            LOG.warn("Simulation '" + unitNameKey + "' was still running while dropped from memory. Therefore the Simulation was stopped");
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Simulation '" + unitNameKey + "' was still running while dropped from memory. Therefore, the Simulation was stopped.");
        }

        fillModel(model);

        return ControllerConstants.PAGE_REDIRECT_LOAD_FROM_DATABASE;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_LOAD_FROM_DATABASE + "/save", method = RequestMethod.POST)
    public String saveToDatabase(Map<String, Object> model, @RequestParam String unitNameKey, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {


        ec.getSimulationFunctionFor(unitNameKey);
        List<Interval> intervals = ec.getIntervalsFor(unitNameKey);

        Double startingBound = intervals.get(0).getStartingValue();
        Double targetBound = intervals.get(0).getTargetValue();
        int period = intervals.get(0).getTimeFrame();
        Double lowerNoiseBound = ec.getNoiseFor(unitNameKey).getLowerNoiseBound();
        Double upperNoiseBound = ec.getNoiseFor(unitNameKey).getUpperNoiseBound();
        String simulationFunction = ec.getSimulationFunctionFor(unitNameKey).getClass().getSimpleName();
        //saving additional is interval not implemented yet

        int additionalPeriod = 0;
        Double additionalLowerNoiseBound = 0.0;
        Double additionalUpperNoiseBound = 0.0;
        if (intervals.size() >= 2) {
            Interval interval = intervals.get(1);
            additionalPeriod = interval.getTimeFrame();
            additionalLowerNoiseBound = interval.getStartingValue();
            additionalUpperNoiseBound = interval.getTargetValue();
        }


        simulationValuesService.saveNewSimulationValues(new SimulationValues(unitNameKey, simulationFunction,
                startingBound, targetBound, period, lowerNoiseBound, upperNoiseBound, additionalLowerNoiseBound, additionalUpperNoiseBound, additionalPeriod));

        redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Setup was saved!");
        fillModel(model);

        return ControllerConstants.PAGE_REDIRECT_LOAD_FROM_DATABASE;
    }


    private void fillModel(Map<String, Object> model) {
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_NAMEKEYS, simulationValuesService.findAllNameKeys());
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());
    }


}
