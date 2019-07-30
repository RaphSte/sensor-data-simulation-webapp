package de.sybit.machinesimulator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.sybit.machinesimulator.entities.SimulationValues;
import de.sybit.machinesimulator.exception.ResourceNotFoundException;
import de.sybit.machinesimulator.service.ObjectNameService;
import de.sybit.machinesimulator.service.SimulationValuesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.sybit.machinesimulator.service.SimulationSetUpService;

@Controller
public class SimulationSetUpController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SimulationSetUpController.class);


    @Autowired
    SimulationSetUpService simulationSetUpService;

    @Autowired
    ObjectNameService nameService;

    @Autowired
    SimulationValuesService simulationValuesService;


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN +"/"+ControllerConstants.PAGE_SIMULATION_SET_UP, method = RequestMethod.GET)

    public String simulationSetUp(Map<String, Object> model, HttpServletRequest request) {
        fillModel(model);
        return ControllerConstants.PAGE_SIMULATION_SET_UP;
    }

    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_SIMULATION_SET_UP + "/"
            + ControllerConstants.SET_UP, method = RequestMethod.POST)
    public String simulationSetUp(@RequestParam String unitName, @RequestParam String simulationFunction,
                                  @RequestParam String lowerIntervalBound, @RequestParam String upperIntervalBound, @RequestParam String period,
                                  @RequestParam String lowerNoiseBound, @RequestParam String upperNoiseBound, @RequestParam String saveToDatabase,
                                  Map<String, Object> model, HttpServletRequest request, RedirectAttributes redir) {

        simulationSetUpService.setUpUnit(unitName);
        simulationSetUpService.setUpNoise(unitName, lowerNoiseBound, upperNoiseBound);
        simulationSetUpService.setUpInterval(unitName, lowerIntervalBound, upperIntervalBound, period);
        simulationSetUpService.setUpSimulationFunction(unitName, simulationFunction);
        simulationSetUpService.wrapUpSetUp(unitName);


        if (saveToDatabase.equals("true")) {
            simulationValuesService.saveNewSimulationValues(new SimulationValues(unitName, simulationFunction, Double.parseDouble(lowerIntervalBound),
                    Double.parseDouble(upperIntervalBound), Integer.parseInt(period), Double.parseDouble(lowerNoiseBound), Double.parseDouble(upperNoiseBound)));
            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Setup was saved!");
        }

        if (simulationSetUpService.setUpWasSuccessful()) {
            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Simulation was set up successfully!");
        } else {
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Simulation set up failed!");
        }

        return ControllerConstants.PAGE_REDIRECT_SIMULATION_SET_UP;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN +"/"+ControllerConstants.PAGE_SIMULATION_SET_UP + "/"
            + ControllerConstants.SET_UP_ADDITIONAL_INTERVAL, method = RequestMethod.POST)
    public String additionalInterval(@RequestParam String lowerIntervalBound, @RequestParam String upperIntervalBound,
                                     @RequestParam String period, @RequestParam String unitName, @RequestParam String saveToDatabase,
                                     Map<String, Object> model, RedirectAttributes redir) {


        //Save in memory
        simulationSetUpService.setUpAdditionalInterval(unitName, lowerIntervalBound, upperIntervalBound, period);
        redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Additional interval was set successfully!");

        //Save to database
        if (saveToDatabase.equals("true")) {
            try {
                SimulationValues simulationValues = simulationValuesService.findByNameKey(unitName);
                simulationValues.setAdditional_interval_lower_bound(Double.parseDouble(lowerIntervalBound));
                simulationValues.setAdditional_interval_upper_bound(Double.parseDouble(upperIntervalBound));
                simulationValues.setAdditional_interval_period(Integer.parseInt(period));
                simulationValuesService.updateSimulationValues(simulationValues);
                LOG.info("Setup was saved.");
                redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Setup was saved!");
            } catch (ResourceNotFoundException e) {
                LOG.error("Unable to save additional interval setup. Resource does not exist.");
                redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Unable to save additional interval setup. Resource does not exist.");
            }
        }


        fillModel(model);
        return ControllerConstants.PAGE_REDIRECT_SIMULATION_SET_UP;
    }

    private void fillModel(Map<String, Object> model) {
        model.put(ControllerConstants.MODEL_KEY_CLASSNAMES, nameService.provideSimulationTypes());
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());
    }

}



