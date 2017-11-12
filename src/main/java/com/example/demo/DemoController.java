package com.example.demo;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class DemoController {

    @Autowired
    private List<DemoModel> context;

    @RequestMapping("/getFieldData")
    public ResponseEntity<?> getFieldData(@RequestParam(name = "id") int id) throws MqttException {
        return new ResponseEntity<>(context.get(id), HttpStatus.OK);
    }
}