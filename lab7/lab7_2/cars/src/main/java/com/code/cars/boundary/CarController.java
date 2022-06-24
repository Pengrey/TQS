package com.code.cars.boundary;

import com.code.cars.data.Car;
import com.code.cars.service.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarManagerService service;

    @GetMapping
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved_car = service.save(car);
        return new ResponseEntity<>(saved_car, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value="id") Long carId){
        return ResponseEntity.ok().body(service.getCarDetails(carId).get());
    }
}
