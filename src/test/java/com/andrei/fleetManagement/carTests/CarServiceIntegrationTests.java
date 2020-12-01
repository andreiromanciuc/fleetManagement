package com.andrei.fleetManagement.carTests;

import com.andrei.fleetManagement.domain.Car;
import com.andrei.fleetManagement.service.CarService;
import com.andrei.fleetManagement.transfer.CreateCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.cert.CertificateRevokedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class CarServiceIntegrationTests {

    @Autowired
    private CarService carService;

    @Test
    public void createCar_whenValidRequest_thenCarIsCreated() {
        CreateCar createCar = new CreateCar();
        createCar.setModel("Ford");
        createCar.setPlateNumber("CJ 12 ASD");
        createCar.setVinNumber("WF0123459DF1233");
        createCar.setMileage(123456);

        Car car = carService.createCar(createCar);

        assertThat(car, notNullValue());
        assertThat(car.getId(), greaterThan(0L));
        assertThat(car.getMileage(), is(createCar.getMileage()));
        assertThat(car.getVinNumber(), is(createCar.getVinNumber()));
        assertThat(car.getModel(), is(createCar.getModel()));
        assertThat(car.getPlateNumber(), is(createCar.getPlateNumber()));
    }
}
