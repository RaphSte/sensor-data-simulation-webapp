/**
 * Copyright (c) 2019 Sybit GmbH. All rights reserved.
 *
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.sybit.machinesimulator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.sybit.machinesimulator.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SimulationSetUpService simulationSetUpService;

    @Autowired
    private TransitionSetUpService transitionSetUpService;

    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model,
                          HttpServletRequest request, HttpServletResponse response) {

        return home(model, request, response);
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_HOME, method = RequestMethod.GET)
    public String home(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        String page = ControllerConstants.PAGE_HOME;
        return page;
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_HOME + "/sampleSetup", method = RequestMethod.GET)
    public String home2(Map<String, Object> model,
                        HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {

        try {
            setUpTestUnits();
            redir.addFlashAttribute(ControllerConstants.MSG_INFO, "Added Sample Setups");
        } catch (Exception e) {
            redir.addFlashAttribute(ControllerConstants.MSG_ERROR, "Could not add sample Setups because of " + e.getMessage()+".");
            e.printStackTrace();
        }


        return ControllerConstants.PAGE_REDIRECT_HOME;
    }

    private void setUpTestUnits() {
        //--------------------------testUnitSetup-----------------------------------------
        simulationSetUpService.setUpUnit("testUnitMachine1");
        simulationSetUpService.setUpNoise("testUnitMachine1", "0", "0");
        simulationSetUpService.setUpInterval("testUnitMachine1", "20", "30", "10");
        simulationSetUpService.setUpSimulationFunction("testUnitMachine1", "Sine");
        simulationSetUpService.wrapUpSetUp("testUnitMachine1");

        //--------------------------testUnitSetup-----------------------------------------
        simulationSetUpService.setUpUnit("testUnitMachine2");
        simulationSetUpService.setUpNoise("testUnitMachine2", "0.5", "0.5");
        simulationSetUpService.setUpInterval("testUnitMachine2", "5", "25", "20");
        simulationSetUpService.setUpSimulationFunction("testUnitMachine2", "Sine");
        simulationSetUpService.wrapUpSetUp("testUnitMachine2");

        //--------------------------------------------------------------------------------

        //--------------------------testUnitSetup-----------------------------------------
        simulationSetUpService.setUpUnit("testUnitMachine3");
        simulationSetUpService.setUpNoise("testUnitMachine3", "0", "0");
        simulationSetUpService.setUpInterval("testUnitMachine3", "20", "30", "5");
        simulationSetUpService.setUpSimulationFunction("testUnitMachine3", "Linear");
        simulationSetUpService.setUpAdditionalInterval("testUnitMachine3", "30", "20", "5");
        simulationSetUpService.wrapUpSetUp("testUnitMachine3");


        transitionSetUpService.setUpInterval("testUnitMachine3", "20", "30", "5");
        transitionSetUpService.setUpAdditionalInterval("testUnitMachine3", "30", "20", "15");
        //--------------------------------------------------------------------------------
    }


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN + "/" + ControllerConstants.PAGE_HOME, method = RequestMethod.POST)
    public String homePost(
            @RequestParam String upperBound, @RequestParam String lowerBound, @RequestParam String period, @RequestParam String unit,
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

        System.out.println(upperBound + lowerBound + period + unit);

        String page = ControllerConstants.PAGE_HOME;


        return page;
    }
}
