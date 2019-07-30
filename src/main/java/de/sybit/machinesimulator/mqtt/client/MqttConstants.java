package de.sybit.machinesimulator.mqtt.client;

public class MqttConstants {

    
    public static final String DEFAULT_HIVE_MQ_SERVER_URI = "tcp://broker.hivemq.com:1883";
    public static final String DEFAULT_HIVE_MQ_SERVER_URI_WS = "ws://broker.hivemq.com:8000";

    public static final String DEFAULT_SERVER_URI = DEFAULT_HIVE_MQ_SERVER_URI;
    public static final boolean DEFAULT_AUTOMATIC_RECONNECT = true;
    public static final boolean DEFAULT_CLEAN_SESSION = true;
    public static final int DEFAULT_CONNECTION_TIME_OUT = 10;
}
