package com.example.assignment.DTO;

import com.example.assignment.Model.FuelType;
import lombok.Data;

@Data
public class DetailsDTO {
    private String body;
    private String model;
    private ManufacturerDTO manufacturer;
    private int numberOfDoors;
    private FuelType fuelType;
    private String engine;
    private String mileage;
    private String modelYear;
    private String productionYear;
    private String externalColor;
}
