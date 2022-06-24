package com.code.cars.service;

import com.code.cars.data.Car;
import com.code.cars.data.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Car audi = new Car("Audi", "A4");
        audi.setCarId(111L);

        Car honda = new Car("Honda", "Civic");
        Car tesla = new Car("Tesla", "S");

        List<Car> allCars = Arrays.asList(audi, honda, tesla);

        Mockito.when(carRepository.findByCarId(1337L)).thenReturn(null);
        Mockito.when(carRepository.findByCarId(audi.getCarId())).thenReturn(audi);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car foundCar = service.getCarDetails(111L).orElse(null);

        assertThat(foundCar.getCarId()).isEqualTo(111L);
        verifyFindByCarIdIsCalledOnce();
    }

    @Test
    public void whenSearchInvalidId_thenCarShouldNotBeFound() {
        Car foundCar = service.getCarDetails(1377L).orElse(null);

        assertThat(foundCar).isEqualTo(null);
    }

    @Test
    public void whenGetAllCars_GetAllCars() {
        Car audi = new Car("Audi","A4");
        Car honda = new Car("Honda","Civic");
        Car tesla = new Car("Tesla","S");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(audi.getMaker(), honda.getMaker(), tesla.getMaker());
    }

    private void verifyFindByCarIdIsCalledOnce() {
        Mockito.verify(carRepository, times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}
