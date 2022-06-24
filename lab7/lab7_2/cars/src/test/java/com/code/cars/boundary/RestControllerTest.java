package com.code.cars.boundary;

import com.code.cars.data.Car;
import com.code.cars.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest
public class RestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
    @Test
    public void whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = createTestCar("Honda","Civic");
        Car car2 = createTestCar("Audi","A4");

        when(service.getAllCars()).thenReturn(Arrays.asList(car1, car2));

        RestAssuredMockMvc.when().get("/api/cars").then().body("maker", hasItems("Honda","Audi")).statusCode(200);

        verify(service, VerificationModeFactory.times(1)).getAllCars();
    }

    @Test
    private Car createTestCar(String maker, String model) {
        return new Car(maker, model);
    }
}
