package com.api.carapi.services.Impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carapi.entities.Car;
import com.api.carapi.entities.dto.CarDTO;
import com.api.carapi.repositories.CarRepository;
import com.api.carapi.services.CarService;
import com.api.carapi.services.exceptions.ObjectNotFoundException;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarRepository repository;
	
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public Car findById(Long id) {
		Optional<Car> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	@Override
	public List<Car> findAll() {
		return repository.findAll();
	}

	@Override
	public Car create(CarDTO obj) {
	  return repository.save(mapper.map(obj,Car.class));
	}
	
	

	@Override
	public Car update(CarDTO obj) {
		 return repository.save(mapper.map(obj, Car.class));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
