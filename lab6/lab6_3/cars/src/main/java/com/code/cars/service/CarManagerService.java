package com.code.cars.service;

import com.code.cars.data.Car;
import com.code.cars.data.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        Car car = carRepository.findByCarId(id);
        Optional<Car> c;
        if(car!=null){
            c = Optional.of(car);
        }
        else{
            c = Optional.empty();
        }
        return c;
    }
}
