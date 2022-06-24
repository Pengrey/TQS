package com.code.cars.boundary;

import com.code.cars.data.Car;
import com.code.cars.data.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;




import java.util.List;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
@TestPropertySource(locations="application-integrationtest.properties")
public class CarControllerTestIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetRepository(){
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar(){
        Car car = new Car("Audi","A4");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);

        assertThat(repository.findAll()).extracting(Car::getModel).containsOnly("A4");
    }

    @Test
    void givenCar_whenGetCar_thereIsCar(){
        createTestCar("Audi","A4");
        createTestCar("Honda","Civic");

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("A4","Civic");
    }

    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }
}
