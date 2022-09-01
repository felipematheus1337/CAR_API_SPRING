package com.api.carapi.services.Impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.carapi.entities.Car;
import com.api.carapi.entities.dto.CarDTO;
import com.api.carapi.repositories.CarRepository;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarServiceImplTest {
	
	private static final Long ID     = 1L;
    private static final Integer INDEX   = 0;
    private static final String MODEL     = "F50";
    private static final String MAKE    = "FERRARI";
    private static final String COLOR = "RED";

    private static final Integer YEAR = 2019;
    
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    
	
	
	@InjectMocks
	private CarServiceImpl service;
	
	@Mock
	private CarRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private Car car;
	private CarDTO carDTO;
	private Optional<Car> optionalCar;
	
	
	
	
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCar();
	}






	private void startCar() {
		car = new Car(ID,MODEL,YEAR,MAKE,COLOR);
		carDTO = new CarDTO(ID,MODEL,YEAR,MAKE,COLOR);
		optionalCar = Optional.of(new Car(ID,MODEL,YEAR,MAKE,COLOR));
		
	}
	
	@Test
	void whenFindByIdThenReturnAnCarInstance() {
	 when(repository.findById(anyLong())).thenReturn(optionalCar);
	 
	 
	 Car response = service.findById(ID);
	 
	 
	 assertNotNull(response);
	 
	 assertEquals(Car.class,response.getClass());
	 assertEquals(ID, response.getId());
     assertEquals(MAKE, response.getMake());
     assertEquals(MODEL, response.getModel());
     assertEquals(COLOR,response.getColor());
	 
	 
	 
	 
	 
	 
	}

	

}
