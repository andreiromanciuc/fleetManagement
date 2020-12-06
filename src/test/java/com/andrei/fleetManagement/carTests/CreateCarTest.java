package com.andrei.fleetManagement.carTests;

import com.andrei.fleetManagement.transfer.CreateCar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateCarTest {

    @Test
    public CreateCar createCar(){
        CreateCar createCar = new CreateCar();
        createCar.setModel("Ford");
        createCar.setPlateNumber("CJ 12 ASD");
        createCar.setVinNumber("WF0123459DF1233");
        createCar.setMileage(123456);
        return  createCar;
    }
}
