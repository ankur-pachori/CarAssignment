package com.example.assignment.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Manufacturer {
    @Id
    @SequenceGenerator(name = "Manufacturer_seq",sequenceName = "Manufacturer_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Manufacturer_seq")
    private Long id;

    private Long code;
    private String name;

}
