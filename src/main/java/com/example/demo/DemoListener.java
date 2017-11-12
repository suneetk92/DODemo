package com.example.demo;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements MqttCallback {

    @Autowired
    private DemoService service;

    private MqttClient client;

    public DemoListener() {
        try {
            client = new MqttClient("tcp://139.59.3.242:1883", "pahoReceiver");
            client.connect();
            client.setCallback(this);
            client.subscribe("foo");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String str = new String(message.getPayload());
        service.storeInContext(str);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub
    }

}
