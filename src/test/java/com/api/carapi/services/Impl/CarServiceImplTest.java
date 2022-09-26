package com.api.carapi.services.Impl;

import com.api.carapi.entities.Car;
import com.api.carapi.entities.dto.CarDTO;
import com.api.carapi.repositories.CarRepository;
import com.api.carapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
	
	@Test
	void whenFindByIdThenThrowsObjectNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.findById(2L);
			
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundException.class,ex.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO,ex.getMessage());
		}
	}
	
	
	@Test
	void whenFindAllThenReturnAListOfCars() {
		when(repository.findAll()).thenReturn(List.of(car));
		
		List<Car> carList = service.findAll();
		
		assertNotNull(carList);
        assertEquals(1, carList.size());
        assertEquals(Car.class, carList.get(INDEX).getClass());

        assertEquals(MODEL,carList.get(0).getModel());
        assertEquals(MAKE,carList.get(0).getMake());
        assertEquals(YEAR,carList.get(0).getYear());
        assertEquals(COLOR,carList.get(0).getColor());
	}

	@Test
	void whenCreateThenReturnSuccess() {
		when(repository.save(any())).thenReturn(car);

		Car response = service.create(carDTO);

		assertNotNull(response);

		assertEquals(MODEL,car.getModel());
		assertEquals(MAKE,car.getMake());
		assertEquals(YEAR,car.getYear());
		assertEquals(COLOR,car.getColor());

		assertEquals(response.getClass(),Car.class);



	}

	@Test
	void whenUpdateThenReturnSucess() {
		when(repository.findById(anyLong())).thenReturn(optionalCar);
		when(repository.save(any())).thenReturn(car);

		Car response = service.update(carDTO);

		assertNotNull(response);
		assertEquals(response.getClass(),Car.class);
		assertEquals(response.getModel(),car.getModel());
		assertEquals(response.getColor(),car.getColor());
		assertEquals(response.getModel(),car.getModel());
	}



	@Test
	void whenUpdateThenReturnDataIntegrityViolationException() {
         when(repository.findById(anyLong()))
				 .thenThrow(new ObjectNotFoundException("Car not found to update!"));

		 try {
			service.update(carDTO);
		 } catch(Exception e) {
           assertEquals(ObjectNotFoundException.class, e.getClass());
		   assertEquals("Car not found to update!",e.getMessage());
		 }
	}

	@Test
	void whenDeleteThenReturnOk() {
      //when(repository.findById(anyLong())).thenReturn(optionalCar);
	  doNothing().when(repository).deleteById(anyLong());
	  service.delete(ID);
      verify(repository,times(1)).deleteById(anyLong());

	}

	@Test
	void whenIfCarExists() {
		when(repository.findById(anyLong())).thenReturn(optionalCar);
		service.IfCarExists(ID);
		verify(repository,times(1)).findById(anyLong());

	}

	@Test
	void whenIfCarDontExists() {
		when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		try {
			service.IfCarExists(ID);
		} catch (Exception e) {
			assertEquals(e.getMessage(),OBJETO_NAO_ENCONTRADO);
			assertEquals(e.getClass(),ObjectNotFoundException.class);
		}

	}



	

}
