package de.sybit.machinesimulator.mqtt.client;


import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import de.sybit.machinesimulator.service.SimulationService;
import de.sybit.machinesimulator.setUp.SimulationConstants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
public class MqttService {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MqttService.class);

    @Autowired
    SimulationEssentialsContainer ec;

    @Autowired
    SimulationService simulationService;

    private Map<String, MqttSettings> mqttSettings = new HashMap<>();

    public void publishFor(String key) {
        MqttSettings settings = mqttSettings.get(key);

        if (mqttSettings.get(key) == null) {
            LOG.warn("There are no mqttSettings for this value. New instance needs to be created first.");
            settings = createNewMqttDefaultSettingsFor(key);
            LOG.warn("Created and applied new Default settings as fallback");
        }
        SybitMqttClient mqttClient = new SybitMqttClient(settings.getServerUri());
        mqttClient.connect(settings.getConnectOptions());
        Callable<Void> mqttPublisher = new MqttPublisher(mqttClient.getClient(), ec, key);
        if (simulationService.isSimulationRunning(key)) {
            new Thread(() -> processPublisherCall(mqttPublisher, key)).start();
        } else {
            LOG.warn("Tried to publish MQTT for '" + key + "'. Simulation is not running, therefore nothing will be published");
        }

    }


    public void setMqttSettingFor(String key, MqttSettings mqttSettings) {
        this.mqttSettings.put(key, mqttSettings);
    }

    public MqttSettings getMqttSettingFor(String key) {
        return mqttSettings.get(key);
    }

    public MqttSettings createNewMqttDefaultSettingsFor(String key) {
        MqttSettings settings = new MqttSettings();
        mqttSettings.put(key, settings);
        return settings;
    }


    private void processPublisherCall(Callable<Void> mqttPublisher, String key) {
        LOG.info("Publishing mqtt-datas for '" + key + "'");
        while (simulationService.isSimulationRunning(key)) {
            try {
                new Thread().sleep(SimulationConstants.SIMULATION_TIME_OFFSET);
                mqttPublisher.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
