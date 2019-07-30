package de.sybit.machinesimulator.mqtt.client;

import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

public class MqttPublisher implements Callable<Void> {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MqttPublisher.class);

    private IMqttClient client;

    SimulationEssentialsContainer ec;

    String key;


    public MqttPublisher(IMqttClient client, SimulationEssentialsContainer ec, String key) {
        this.client = client;
        this.ec = ec;
        this.key = key;
    }

    @Override
    public Void call() throws Exception {
        if (!client.isConnected()) {
            LOG.error("Client is not Connected");
            return null;
        }
        MqttMessage msg = constructMessage();
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(key, msg);
        return null;
    }

    private MqttMessage constructMessage() {
        byte[] payload = ("{" + getTimeStamp() + ", " + getValue() + "}").getBytes();
        return new MqttMessage(payload);
    }

    private String getValue() {
        //dynamic locale would be nice
        NumberFormat nf = DecimalFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(2);
        return toKeyValuePair("value", nf.format(ec.getUnitFor(key).getValue()));
    }

    private String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String date = sdf.format(new Date());
        return toKeyValuePair("timestamp", date);
    }


    private String toKeyValuePair(String key, String value) {
        return "\"" + key + "\": \"" + value + "\"";
    }


}
