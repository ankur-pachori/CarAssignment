package com.example.assignment.DTO;
import com.example.assignment.Model.ConditionOfCar;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;

    private ConditionOfCar conditionOfCar;

    private DetailsDTO details;

    private LocationDTO location;
}
