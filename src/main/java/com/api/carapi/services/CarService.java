package com.api.carapi.services;

import com.api.carapi.entities.Car;
import com.api.carapi.entities.dto.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
	
	Car findById(Long id);
	List<Car> findAll();
    Car create(CarDTO obj);
    Car update(CarDTO obj);
    void delete(Long id);

}
