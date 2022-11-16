package com.example.assignment.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table()
@Data
public class Location {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
//    @Column(name = "location_id", nullable = false)
    private Long id;
    private String loc;
    private String lat;
}
