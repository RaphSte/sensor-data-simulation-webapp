package de.sybit.machinesimulator.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class MqttSettings {


    private String topic;
    private boolean automaticReconnect = MqttConstants.DEFAULT_AUTOMATIC_RECONNECT;
    private boolean cleanSession = MqttConstants.DEFAULT_CLEAN_SESSION;
    private int connectionTimeout = MqttConstants.DEFAULT_CONNECTION_TIME_OUT;
    private String serverUri = MqttConstants.DEFAULT_SERVER_URI;
    private MqttConnectOptions connectOptions;


    public MqttSettings() {
        connectOptions = new MqttConnectOptions();
        connectOptions.setConnectionTimeout(connectionTimeout);
        connectOptions.setCleanSession(cleanSession);
        connectOptions.setAutomaticReconnect(automaticReconnect);
    }

    public void applyNewConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(automaticReconnect);
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(connectionTimeout);
        connectOptions = options;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    public void setAutomaticReconnect(boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public MqttConnectOptions getConnectOptions() {
        return connectOptions;
    }

    public String getServerUri() {
        return serverUri;
    }

    public void setServerUri(String serverUri) {
        this.serverUri = serverUri;
    }
}
