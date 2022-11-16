package com.example.assignment.Controller;

import com.example.assignment.DTO.CarDTO;

import com.example.assignment.DTO.UpdateCar;
import com.example.assignment.Service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
//@Api(value = "/car",tags = "Car Management")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/{id}")
//    @ApiOperation(value = "Get car details",tags = "")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "car is avalible in the list"),
//            @ApiResponse(code = 404, message = "invalid id")
//    })
    public ResponseEntity<?> getCar(@PathVariable(name = "id") Long id) {
        CarDTO responce = carService.getCar(id);
        return new ResponseEntity<CarDTO>(responce,HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @ApiOperation(value = "update car details",tags = "")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "car details is updated"),
//            @ApiResponse(code = 404, message = "invalid id")
//    })
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody(required = true)UpdateCar car) {
        CarDTO responce = carService.updateCar(id,car);
        return new ResponseEntity<CarDTO>(responce,HttpStatus.OK);
    }


    @PostMapping()
//    @ApiOperation(value = "add car to the list",tags = "adding car")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Car Added"),
//            @ApiResponse(code = 400, message = "Some fields are empty")
//    })
    public ResponseEntity<?> createRecord(@RequestBody CarDTO carDTO) {
         CarDTO responce =carService.create(carDTO);
         return new ResponseEntity<CarDTO>(responce, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Remove car details",tags = "")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "car is succecfully removed from list"),
//            @ApiResponse(code = 404, message = "invalid id")
//    })
    public ResponseEntity<?> deleteRecord(@PathVariable("id") Long id) {
        CarDTO responce =carService.deleteRecord(id);
        return new ResponseEntity<CarDTO>(responce, HttpStatus.CREATED);
    }

}
