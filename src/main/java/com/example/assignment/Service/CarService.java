package com.example.assignment.Service;

import com.example.assignment.DTO.CarDTO;
import com.example.assignment.DTO.UpdateCar;
import com.example.assignment.Model.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarService {
    CarDTO getCar(Long id);

    CarDTO updateCar(Long id, UpdateCar car);

    CarDTO create(CarDTO car);

    CarDTO deleteRecord(Long id);
}
