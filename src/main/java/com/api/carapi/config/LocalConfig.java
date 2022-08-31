package com.api.carapi.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.carapi.entities.Car;
import com.api.carapi.repositories.CarRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
    private CarRepository repository;

    @Bean
    public void startDB() {
        Car c1 = new Car(null,"F50",2019,"Ferrari","Red");
        Car c2 = new Car(null,"Murcielago",2017,"Lamborghini","Yellow");

        repository.saveAll(List.of(c1,c2));
    }
}
