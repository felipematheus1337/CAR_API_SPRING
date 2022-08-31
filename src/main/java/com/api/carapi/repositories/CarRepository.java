package com.api.carapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carapi.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

	

}
