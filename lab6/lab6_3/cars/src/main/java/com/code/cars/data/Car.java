package com.code.cars.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tqs_cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @Column
    private String maker;

    @Column
    private String model;

    public Car() {}

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Car(Car givenCar) {
        this.carId = givenCar.getCarId();
        this.model = givenCar.getModel();
        this.maker = givenCar.getMaker();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long cardId) {
        this.carId = cardId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }

    @Override
    public String toString() {
        return "Car{" +
                "cardId=" + carId +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
