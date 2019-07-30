package de.sybit.machinesimulator.controller;

import de.sybit.machinesimulator.mqtt.client.MqttService;
import de.sybit.machinesimulator.service.ObjectNameService;
import de.sybit.machinesimulator.service.SimulationService;
import de.sybit.machinesimulator.setUp.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SimulationStateController {

    @Autowired
    SimulationService simulationService;

    @Autowired
    ObjectNameService nameService;

    @Autowired
    MqttService mqttService;

    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_SIMULATION_STATE, method = RequestMethod.GET)
    public String simulationState(Map<String, Object> model, HttpServletRequest request, RedirectAttributes redir) {
        fillModel(model, redir);
        return ControllerConstants.PAGE_SIMULATION_STATE;
    }

    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_SIMULATION_STATE, method = RequestMethod.POST)
    public String simulationStateStart(@RequestParam int start, @RequestParam String unitName, Map<String, Object> model,
                                       HttpServletRequest request, RedirectAttributes redir) {

        if (start == 1) {
            State.startSimulaton();
            simulationService.setSimulationStateFor(unitName, true);
            simulationService.StartNewSimulationFor(unitName);
            mqttService.publishFor(unitName);
        }

        if (start == 0) {
            State.stopSimulaton();
            simulationService.setSimulationStateFor(unitName, false);
        }

        if (start == 2) {
            simulationService.setErrorStateFor(unitName, true);
        }


        fillModel(model, redir);

        return ControllerConstants.PAGE_SIMULATION_STATE;
    }


    private void fillModel(Map<String, Object> model, RedirectAttributes redir) {

        for (String type : nameService.provideAvailableSimulations()) {

            if (simulationService.isSimulationRunning(type)) {
                redir.addFlashAttribute(ControllerConstants.MODEL_KEY_STATE, "running");
                model.put(ControllerConstants.MODEL_KEY_STATE + type, "running");
            } else {
                redir.addFlashAttribute(ControllerConstants.MODEL_KEY_STATE, "stopped");
                model.put(ControllerConstants.MODEL_KEY_STATE + type, "stopped");
            }
        }
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());
    }


}
