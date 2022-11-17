package com.example.assignment.Service;

import com.example.assignment.DTO.CarDTO;
import com.example.assignment.DTO.UpdateCar;
import com.example.assignment.Model.Car;
import com.example.assignment.Repo.CarRepo;
import com.example.assignment.advice.CarNotFound;
import com.example.assignment.advice.FieldsEmptyException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CarRepo carRepo;

    @Override
    public CarDTO getCar(Long id) {
        Optional<Car> car=carRepo.findById(id);
        if (!car.isPresent()){
            throw new CarNotFound("Invalid Id","404");
        }
        CarDTO carDTO =modelMapper.map(car.get(),CarDTO.class);
        return carDTO;
    }

    @Override
    public CarDTO updateCar(Long id, UpdateCar updateCar) {
        Optional<Car> car = carRepo.findById(id);
        if (!car.isPresent()){
            throw new CarNotFound("Invalid Id","404");
        }
        CarDTO carDTO=modelMapper.map(carRepo.findById(id).get(),CarDTO.class);
        if (updateCar.getConditionOfCar()!=null){
            carDTO.setConditionOfCar(updateCar.getConditionOfCar());
        }
        if (updateCar.getExternalColor() != null){
            carDTO.getDetails().setExternalColor(updateCar.getExternalColor());
        }
        if (updateCar.getLocation() != null){
        if (updateCar.getLocation().getLoc() != null&& updateCar.getLocation().getLat() != null){
            carDTO.setLocation(updateCar.getLocation());
        }
        }
        if(updateCar.getMileage()!=null){
            carDTO.getDetails().setMileage(updateCar.getMileage());
        }
        return carDTO =modelMapper.map(carRepo.save(modelMapper.map(carDTO,Car.class)),CarDTO.class);
    }

    @Override
    public CarDTO create(CarDTO carDTO) {
        if (carDTO.getConditionOfCar()== null || carDTO.getDetails().getModel() == null || carDTO.getDetails().getManufacturer().getName()== null){
            throw new FieldsEmptyException("Input fields are empty","601");
        }
        Car car = this.modelMapper.map(carDTO,Car.class);
        carDTO = modelMapper.map(carRepo.save(car),CarDTO.class);
        return carDTO;
    }

    @Override
    public CarDTO deleteRecord(Long id) {
        Optional<Car> car=carRepo.findById(id);
        if (!car.isPresent()){
            throw new CarNotFound("Invalid Id","404");
        }
        carRepo.deleteById(id);
        CarDTO carDTO = this.modelMapper.map(car.get(),CarDTO.class);
        return carDTO;
    }

}
