package com.example.assignment.Model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table
public class Car {
    @Id
    @SequenceGenerator(name = "Car_seq",sequenceName = "Car_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Car_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Details details;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private ConditionOfCar conditionOfCar;
}
