package com.example.assignment.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Details {
    @Id
    @SequenceGenerator(name = "Details_seq",sequenceName = "Details_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Details_seq")
    private Long id;

    private String body;
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

    private int numberOfDoors;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private String engine;
    private String mileage;
    private String modelYear;
    private String productionYear;
    private String externalColor;

}
