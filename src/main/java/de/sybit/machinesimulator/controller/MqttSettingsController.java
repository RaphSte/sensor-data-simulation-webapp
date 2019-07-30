package de.sybit.machinesimulator.controller;

import de.sybit.machinesimulator.mqtt.client.MqttService;
import de.sybit.machinesimulator.mqtt.client.MqttSettings;
import de.sybit.machinesimulator.service.ObjectNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MqttSettingsController {

    @Autowired
    MqttService mqttService;

    @Autowired
    ObjectNameService nameService;


    @RequestMapping(value = "/" + ControllerConstants.SUB_DOMAIN +"/"+ControllerConstants.PAGE_MQTT_SETTINGS, method = RequestMethod.GET)
    public String mqttSettings(Map<String, Object> model, HttpServletRequest request) {

        fillModel(model);
        return ControllerConstants.PAGE_MQTT_SETTINGS;
    }

    @RequestMapping(value = "/" +ControllerConstants.SUB_DOMAIN +"/"+ ControllerConstants.PAGE_MQTT_SETTINGS, method = RequestMethod.POST)
    public String newMqttSettings(@RequestParam String simulationId, @RequestParam String serverUri, @RequestParam int connectionTimeOut,
                                  @RequestParam String automaticReconnect, @RequestParam String cleanSession, Map<String, Object> model, HttpServletRequest request) {
        MqttSettings settings = mqttService.createNewMqttDefaultSettingsFor(simulationId);

        settings.setTopic(simulationId);
        settings.setServerUri(serverUri);
        settings.setConnectionTimeout(connectionTimeOut);
        settings.setAutomaticReconnect(automaticReconnect.equals("enabled"));
        settings.setCleanSession(cleanSession.equals("enabled"));
        settings.applyNewConnectOptions();

        fillModel(model);
        return ControllerConstants.PAGE_MQTT_SETTINGS;
    }


    private void fillModel(Map<String, Object> model) {
        model.put(ControllerConstants.MODEL_KEY_AVAILABLE_SIMULATIONS, nameService.provideAvailableSimulations());
    }


}
