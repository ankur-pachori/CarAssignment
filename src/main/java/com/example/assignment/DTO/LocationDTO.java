package com.example.assignment.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class LocationDTO {
    private String loc;
    private String lat;
}
