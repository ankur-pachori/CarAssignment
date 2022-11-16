package com.example.assignment.DTO;

import com.example.assignment.Model.ConditionOfCar;
import lombok.Data;

@Data
public class UpdateCar {
    private ConditionOfCar conditionOfCar;
    private String mileage;
    private String externalColor;
    private LocationDTO location;
}
