package com.api.carapi.resources.exceptions;

import com.api.carapi.services.exceptions.DataIntegratyViolationException;
import com.api.carapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    private static final String OBJETO_NAO_ENCONTRADO  = "Objeto não encontrado";
    private static final String OBJETO_INCORRETO = "Objeto incorreto!";


    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void objectNotFoundExceptionThenReturnAnResponseEntity() {
        ResponseEntity<StandardError> response =
                exceptionHandler.objectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/car/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());

    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardError> response =
                exceptionHandler.dataIntegrityViolationException(new DataIntegratyViolationException(OBJETO_INCORRETO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(response.getClass(),ResponseEntity.class);
        assertEquals(response.getBody().getClass(),StandardError.class);
        assertEquals(400,response.getBody().getStatus());
        assertEquals(OBJETO_INCORRETO,response.getBody().getError());
    }


}