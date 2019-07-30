package de.sybit.machinesimulator.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SybitMqttClient {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SybitMqttClient.class);


    private String publisherId = "SimulatorClient"+UUID.randomUUID().toString();
    private IMqttClient client;

    /**
     * @return the client
     */
    public IMqttClient getClient() {
        return client;
    }

    public SybitMqttClient(String serverUri) {
        try {
            client = new MqttClient(serverUri, publisherId);
        } catch (MqttException e) {
            LOG.info("Failed to create new MQTT-Client");
            e.printStackTrace();
        }
    }

    public void connect(MqttConnectOptions options) {
        try {
            client.connect(options);
            LOG.info("client '" + client.getClientId() + "' connected to '" + client.getServerURI() + "'");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
