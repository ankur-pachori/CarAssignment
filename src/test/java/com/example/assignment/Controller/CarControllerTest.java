package com.example.assignment.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import com.example.assignment.DTO.*;
import com.example.assignment.Model.Car;
import com.example.assignment.Model.ConditionOfCar;
import com.example.assignment.Model.FuelType;
import com.example.assignment.Repo.CarRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    ModelMapper modelMapper;

    @MockBean
    private CarRepo repo;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCar() throws Exception {
    Optional<Car>car = Optional.of(modelMapper.map(getCarDTO(),Car.class));
    Mockito.when(repo.findById(any(Long.class))).thenReturn(car);
        MvcResult i = this.mockMvc.perform(get("/car/1"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String s = i.getResponse().getContentAsString();
        CarDTO carDTOCheck = new ObjectMapper().readValue(s,CarDTO.class);
        assertThat(carDTOCheck.getDetails().equals(getCarDTO().getDetails())).isTrue();
        assertThat(carDTOCheck.getId().equals(1l)).isTrue();
        assertThat(carDTOCheck.getLocation().equals(getCarDTO().getLocation())).isTrue();
        assertThat(carDTOCheck.getConditionOfCar().equals(ConditionOfCar.NEW)).isTrue();
        assertThat(carDTOCheck.getDetails().getManufacturer().equals(getCarDTO().getDetails().getManufacturer())).isTrue();

        Mockito.when(repo.findById(any(Long.class))).thenReturn(car.empty());
        i = this.mockMvc.perform(get("/car/2"))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void updateCar() throws Exception {
        UpdateCar updateCar = new UpdateCar();
        updateCar.setConditionOfCar(ConditionOfCar.USED);
        updateCar.setExternalColor("Red");
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLat("000");
        locationDTO.setLoc("000");
        updateCar.setLocation(locationDTO);
        updateCar.setMileage("2000");
        Optional<Car>car = Optional.of(modelMapper.map(getCarDTO(),Car.class));
        Mockito.when(repo.findById(any(Long.class))).thenReturn(car);
        CarDTO carDTO = getCarDTO();
        carDTO.setConditionOfCar(ConditionOfCar.USED);
        carDTO.setLocation(locationDTO);
        carDTO.getDetails().setMileage("2000");
        carDTO.getDetails().setExternalColor("Red");
        Mockito.when(repo.save(any(Car.class))).thenReturn(modelMapper.map(carDTO,Car.class));

        String json = objectMapper.writeValueAsString(updateCar);
        MvcResult i = this.mockMvc.perform(put("/car/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String s = i.getResponse().getContentAsString();
        CarDTO carDTOCheck = new ObjectMapper().readValue(s,CarDTO.class);

        assertThat(carDTOCheck.getDetails().getExternalColor().equals("Red")).isTrue();
        assertThat(carDTOCheck.getLocation().equals(locationDTO)).isTrue();
        assertThat(carDTOCheck.getConditionOfCar().equals(ConditionOfCar.USED)).isTrue();
        assertThat(carDTOCheck.getDetails().getMileage().equals("2000")).isTrue();
    }

    @Test
    void createRecord() throws Exception {
        CarDTO carDTO = getCarDTO();
        Mockito.when(repo.save(any(Car.class))).thenReturn(modelMapper.map(carDTO,Car.class));

        String json = objectMapper.writeValueAsString(carDTO);
        MvcResult i = this.mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn();
        String s = i.getResponse().getContentAsString();
        CarDTO carDTOCheck = new ObjectMapper().readValue(s,CarDTO.class);
        assertThat(carDTOCheck.getDetails().equals(carDTO.getDetails())).isTrue();
        assertThat(carDTOCheck.getId().equals(1l)).isTrue();
        assertThat(carDTOCheck.getLocation().equals(carDTO.getLocation())).isTrue();
        assertThat(carDTOCheck.getConditionOfCar().equals(ConditionOfCar.NEW)).isTrue();
        assertThat(carDTOCheck.getDetails().getManufacturer().equals(carDTO.getDetails().getManufacturer())).isTrue();

        carDTO.setConditionOfCar(null);
        json = objectMapper.writeValueAsString(carDTO);
        i = this.mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void deleteRecord() throws Exception {
        Optional<Car>car = Optional.of(modelMapper.map(getCarDTO(),Car.class));
        Mockito.when(repo.findById(any(Long.class))).thenReturn(car);

        MvcResult i = this.mockMvc.perform(delete("/car/1"))
                .andExpect(status().isCreated())
                .andReturn();
        String s = i.getResponse().getContentAsString();
        CarDTO carDTOCheck = new ObjectMapper().readValue(s,CarDTO.class);
        assertThat(carDTOCheck.getDetails().equals(getCarDTO().getDetails())).isTrue();
        assertThat(carDTOCheck.getId().equals(1l)).isTrue();
        assertThat(carDTOCheck.getLocation().equals(getCarDTO().getLocation())).isTrue();
        assertThat(carDTOCheck.getConditionOfCar().equals(ConditionOfCar.NEW)).isTrue();
        assertThat(carDTOCheck.getDetails().getManufacturer().equals(getCarDTO().getDetails().getManufacturer())).isTrue();

        Mockito.when(repo.findById(any(Long.class))).thenReturn(car.empty());
        i = this.mockMvc.perform(delete("/car/2"))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    private CarDTO getCarDTO(){
        CarDTO carDTO = new CarDTO();
        carDTO.setConditionOfCar(ConditionOfCar.NEW);
        carDTO.setId(1l);
        DetailsDTO detailsDTO = new DetailsDTO();
        detailsDTO.setMileage("1000");
        detailsDTO.setExternalColor("white");
        detailsDTO.setBody("sedan");
        detailsDTO.setModel("sonnet");
        detailsDTO.setEngine("2.5l");
        detailsDTO.setFuelType(FuelType.Gasoline);
        detailsDTO.setModelYear("2022");
        detailsDTO.setProductionYear("2020");
        detailsDTO.setNumberOfDoors(4);
        carDTO.setDetails(detailsDTO);
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setCode(104l);
        manufacturerDTO.setName("pia");
        detailsDTO.setManufacturer(manufacturerDTO);
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLoc("111");
        locationDTO.setLat("222");
        carDTO.setLocation(locationDTO);
        return carDTO;
    }
}