package de.sybit.machinesimulator.controller;

import de.sybit.machinesimulator.service.ObjectNameService;
import de.sybit.machinesimulator.service.TransitionSetUpService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TransitionSetUpController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TransitionSetUpController.class);

    @Autowired
    TransitionSetUpService transitionSetUpService;

    @Autowired
    ObjectNameService nameService;


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_TRANSITION_SET_UP, method = RequestMethod.GET)
    public String transitionSetUp(Map<String, Object> model, HttpServletRequest request) {
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());
        return ControllerConstants.PAGE_TRANSITION_SET_UP;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_TRANSITION_SET_UP + "/"
            + ControllerConstants.SET_UP, method = RequestMethod.POST)
    public String transitionSetUp(@RequestParam String simulationFunction, @RequestParam String lowerIntervalBound, @RequestParam String upperIntervalBound,
                                  @RequestParam String period, Map<String, Object> model, HttpServletRequest request, RedirectAttributes redir) {

        transitionSetUpService.setUpInterval(simulationFunction, lowerIntervalBound, upperIntervalBound, period);

        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());

        if (transitionSetUpService.setUpWasSuccessful()) {
            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Transition was set up successfully!");
            LOG.info("Transition for '" + simulationFunction + "' was set up successfully!");
        } else {
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Transition  set up failed!");
            LOG.info("Transition setup for '" + simulationFunction + "' failed!");
        }
        return ControllerConstants.PAGE_REDIRECT_TRANSITION_SET_UP;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_TRANSITION_SET_UP + "/"
            + ControllerConstants.SET_UP_ADDITIONAL_INTERVAL, method = RequestMethod.POST)
    public String additionalInterval(@RequestParam String lowerIntervalBound, @RequestParam String upperIntervalBound,
                                     @RequestParam String period, @RequestParam String unitNameKey, Map<String, Object> model, HttpServletRequest request,
                                     RedirectAttributes redir) {
        redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Additional interval was set successfully!");
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());

        transitionSetUpService.setUpAdditionalInterval(unitNameKey, lowerIntervalBound, upperIntervalBound, period);

        return ControllerConstants.PAGE_REDIRECT_TRANSITION_SET_UP;
    }

}
