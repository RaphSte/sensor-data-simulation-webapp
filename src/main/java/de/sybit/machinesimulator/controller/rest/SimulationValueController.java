package de.sybit.machinesimulator.controller.rest;

import de.sybit.machinesimulator.service.SimulationService;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationValueController {

    @Autowired
    private SimulationService simulationService;

    @RequestMapping(value = "/getSimulatedDataFor", method = RequestMethod.GET)
    public String getSimulatedDataFor(@RequestParam String unitNameKey) {
        boolean running = simulationService.isSimulationRunning(unitNameKey);
        double value = simulationService.getValueFor(unitNameKey, SimulationConstants.MAXIMUM_FRACTION_DIGITS);
        return "{" + toKeyValuePair("running", Boolean.toString(running)) + ", " + toKeyValuePair("value", Double.toString(value)) + "}";
    }

    private String toKeyValuePair(String key, String value) {
        return "\"" + key + "\": \"" + value + "\"";
    }


}
