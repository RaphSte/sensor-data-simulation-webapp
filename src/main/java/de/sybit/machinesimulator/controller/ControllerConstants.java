/**
 * Copyright (c) 2019 Sybit GmbH. All rights reserved.
 *
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.sybit.machinesimulator.controller;

public class ControllerConstants {


    private ControllerConstants() {
        // this class should not be instantiated
    }

    //SUB_DOMAIN is not needed, ill leave it in, just in case someone does.
    public static final String SUB_DOMAIN = "";

    public static final String PAGE_SIMULATION_SET_UP = "simulation_set_up";

    public static final String PAGE_TRANSITION_SET_UP = "transition_set_up";

    public static final String PAGE_SIMULATION_STATE = "simulation_state";

    public static final String PAGE_REDIRECT_SIMULATION_SET_UP = "redirect:/"+ SUB_DOMAIN +"simulation_set_up";

    public static final String PAGE_REDIRECT_TRANSITION_SET_UP = "redirect:/"+ SUB_DOMAIN +"transition_set_up";

    public static final String PAGE_REDIRECT_LOAD_FROM_DATABASE = "redirect:/"+ SUB_DOMAIN +"database_memory_settings";

    public static final String PAGE_MQTT_SETTINGS = "mqtt_settings";

    public static final String PAGE_LOAD_FROM_DATABASE = "database_memory_settings";

    public static final String PAGE_REDIRECT_SIMULATION_STATE = "redirect:/"+ SUB_DOMAIN +"simulation_state";

    public static final String MSG_INFO = "infoMsg";

    public static final String MSG_ERROR = "errorMsg";

    public static final String MODEL_KEY_STATE = "state";

    public static final String MODEL_KEY_CLASSNAMES = "classNames";

    public static final String MODEL_KEY_AVAILABLE_SIMULATIONS = "availableSimulations";

    public static final String MODEL_KEY_AVAILABLE_NAMEKEYS = "availableNameKeys";

    public static final String SET_UP = "set_up";

    public static final String SET_UP_ADDITIONAL_INTERVAL = "interval";

    public static final String PAGE_LOGIN = "login";

    public static final String PAGE_REDIRECT_LOGIN = "redirect:/login";

    public static final String PAGE_REDIRECT_HOME = "redirect:/"+ SUB_DOMAIN +"home";

    public static final String PAGE_HOME = "home";

}
