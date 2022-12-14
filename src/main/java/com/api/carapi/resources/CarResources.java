package com.api.carapi.resources;

import com.api.carapi.entities.dto.CarDTO;
import com.api.carapi.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/car")
public class CarResources {
	
	private static final String ID = "/{id}";
			
			@Autowired
			private ModelMapper mapper;


	@Autowired
	private CarService service;
	        
	        	
	        @GetMapping(value = ID)
	        public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
	            return ResponseEntity.ok().body(mapper.map(service.findById(id), CarDTO.class));
	        }

	        @GetMapping
	        public ResponseEntity<List<CarDTO>> findAll() {
	            return ResponseEntity.ok().body(service.findAll()
	                    .stream().map(x -> mapper.map(x, CarDTO.class)).collect(Collectors.toList()));
	        }

	        @PostMapping
	        public ResponseEntity<CarDTO> create(@RequestBody CarDTO obj) {
				service.create(obj);
	            return ResponseEntity.ok().body(mapper.map(service.create(obj),CarDTO.class));
	        }

	        @PutMapping(value = ID)
	        public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarDTO obj) {
	            obj.setId(id);
	            return ResponseEntity.ok().body(mapper.map(service.update(obj), CarDTO.class));
	        }

	        @DeleteMapping(value = ID)
	        public ResponseEntity<CarDTO> delete(@PathVariable Long id) {
	            service.delete(id);
	            return ResponseEntity.noContent().build();
	        }
	

}
