package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DemoConfig {

    @Bean
    public List<DemoModel> context() {
        List<DemoModel> list = new ArrayList<>();
        DemoModel model = new DemoModel();
        model.setTemp(123.0);
        model.setLatitude(123.0);
        model.setLongitude(123.0);
        list.add(model);
        list.add(model);
        return list;
    }
}
