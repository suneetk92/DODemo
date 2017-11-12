package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private List<DemoModel> context;

    private MqttClient client;

    public DemoService() {
        try {
            client = new MqttClient("tcp://139.59.3.242:1883", "pahoSender");
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void storeInContext(String message) {
        System.out.println("Inside service");
        DemoModel data;
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(message , DemoModel.class);
            context.set(data.getId(), data);
            if (data.getTemp() > 200) {
                sendMessage("bar", new MqttMessage(("{\"id\": " + data.getId()+ ", \"status\": \"on\"}").getBytes()));
            } else {
                sendMessage("bar", new MqttMessage(("{\"id\": " + data.getId()+ ", \"status\": \"off\"}").getBytes()));
            }
            context.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String topic, MqttMessage message) {
        try {
            System.out.println(message);
            client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
