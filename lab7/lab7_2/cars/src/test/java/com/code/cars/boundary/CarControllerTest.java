package com.code.cars.boundary;

import com.code.cars.data.Car;
import com.code.cars.service.CarManagerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = createTestCar("Honda","Civic");
        Car car2 = createTestCar("Audi","A4");

        when(service.getAllCars()).thenReturn(Arrays.asList(car1, car2));

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maker", is("Honda")))
                .andExpect(jsonPath("$[1].maker", is("Audi")));

        verify(service, VerificationModeFactory.times(1)).getAllCars();
    }

    @Test
    private Car createTestCar(String maker, String model) {
        return new Car(maker, model);
    }
}
