package com.api.carapi.resources;

import com.api.carapi.entities.Car;
import com.api.carapi.entities.dto.CarDTO;
import com.api.carapi.services.Impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CarResourcesTest {

    private static final Long ID = 1L;

    private static final String MODEL = "FERRARI";

    private static final Integer YEAR = 2005;

    private static final String MAKE = "F50";

    private static final  String COLOR = "RED";

    private static final Integer INDEX   = 0;



    @InjectMocks
    private CarResources resource;

    @Mock
    private CarServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Car car = new Car();
    private CarDTO carDTO = new CarDTO();







    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCar();

    }

    @Test
    void whenfindByIdReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(car);
        when(mapper.map(any(),any())).thenReturn(carDTO);
        ResponseEntity<CarDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getClass(),ResponseEntity.class);
        assertEquals(response.getBody().getClass(),carDTO.getClass());

        assertEquals(MODEL,carDTO.getModel());
        assertEquals(MAKE,carDTO.getMake());
        assertEquals(YEAR,carDTO.getYear());
        assertEquals(COLOR,carDTO.getColor());




    }

    @Test
    void findAllThenReturnSuccess() {
        when(service.findAll()).thenReturn(List.of(car));
        when(mapper.map(any(),any())).thenReturn(carDTO);

        ResponseEntity<List<CarDTO>> response = resource.findAll();

        assertNotNull(response);



        assertEquals(response.getClass(),ResponseEntity.class);
        assertEquals(response.getStatusCodeValue(),200);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().get(INDEX).getClass(),CarDTO.class);


    }

    @Test
    void createThenReturnSuccess() {
     when(service.create(any())).thenReturn(car);

     ResponseEntity<CarDTO> response = resource.create(carDTO);


     assertNotNull(response);
     assertEquals(response.getStatusCode(),HttpStatus.CREATED);
     assertEquals(response.getStatusCodeValue(),201);
     assertEquals(response.getClass(),ResponseEntity.class);


    }

    @Test
    void updateThenReturnSuccess() {
        when(service.update(carDTO)).thenReturn(car);
        when(mapper.map(any(),any())).thenReturn(carDTO);

        ResponseEntity<CarDTO> response = resource.update(ID,carDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CarDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(MODEL, response.getBody().getModel());
        assertEquals(COLOR, response.getBody().getColor());
    }

    @Test
    void deleteThenReturnSuccess() {
        doNothing().when(service).delete(ID);
        ResponseEntity<CarDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyLong());
    }

    private void startCar() {
        car = new Car(ID,MODEL,YEAR,MAKE,COLOR);
        carDTO = new CarDTO(ID,MODEL,YEAR,MAKE,COLOR);

    }
}