package com.code.cars.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void whenFindAudiByCarID_thenReturnAudiCar() {
        // arrange a new car and insert into db
        Car car = new Car("Audi","A4");

        entityManager.persistAndFlush(car); //ensure data is persisted at this point

        // test the query method of interest
        Car found = carRepository.findByCarId(car.getCarId());
        assertThat( found ).isEqualTo( car );
    }

    @Test
    public void whenInvalidCarId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-111L);
        assertThat( fromDb ).isNull();
    }


    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car audi = new Car("Audi","A4");
        Car honda = new Car("Honda","Civic");
        Car tesla = new Car("Tesla","S");

        entityManager.persist(audi);
        entityManager.persist(honda);
        entityManager.persist(tesla);
        entityManager.flush();

        List <Car> allCars = carRepository.findAll();
        assertThat( allCars ).hasSize(3).extracting(Car::getMaker).containsOnly(audi.getMaker(), honda.getMaker(), tesla.getMaker());
    }
}
