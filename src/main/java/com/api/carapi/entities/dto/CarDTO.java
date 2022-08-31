package com.api.carapi.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
	
	private String model;
	
	private Integer year;
	
	private String make;
	
	private String color;

}
