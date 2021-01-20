package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Car;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.CarRepository;
import com.andrei.fleetManagement.transfer.CreateCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;
    private final CustomerService customerService;

    public CarService(CarRepository carRepository, CustomerService customerService) {
        this.carRepository = carRepository;
        this.customerService = customerService;
    }

    public Car createCar(long customerId, CreateCar car) {
        LOGGER.info("Creating new car");
        Car newCar = new Car();
        newCar.setMileage(car.getMileage());
        newCar.setModel(car.getModel().toUpperCase());
        newCar.setPlateNumber(car.getPlateNumber().toUpperCase());
        newCar.setVinNumber(car.getVinNumber().toUpperCase());
        newCar.setCustomer(customerService.getCustomerById(customerId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        newCar.setCreatedBy(authentication.getName());

        newCar.setType(car.getType().toUpperCase());

        return carRepository.save(newCar);
    }

    public Car getCarByPlateNumber(String plateNumber) {
        LOGGER.info("Retrieving car by plate number");
        return carRepository.findByPlateNumber(plateNumber);
    }

    public Car getCarByVin(String vinNumber) {
        LOGGER.info("Retrieving car by vin code");
        return carRepository.findByVinNumber(vinNumber);
    }

    public Car getCarById(long id) {
        LOGGER.info("Retrieving car by id {}", id);
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Car "+id+ " is not existing"));
    }

    public Car updateMileageOfTheCarByVin(long id, CreateCar car){
        LOGGER.info("Updating mileage of the car {}", id);
        Car updatedCar = carRepository.findByVinNumber(car.getVinNumber());
        updatedCar.setMileage(car.getMileage());
        return carRepository.save(updatedCar);
    }

    public Car updateMileageOfTheCarByPlate(long id, CreateCar car) {
        LOGGER.info("Updating mileage of the car {}", id);
        Car updatedCar = carRepository.findByPlateNumber(car.getPlateNumber());
        updatedCar.setMileage(car.getMileage());
        return carRepository.save(updatedCar);
    }

    public Car updatePlaceNumberOfTheCar(long id, CreateCar car){
        LOGGER.info("Updating plate number of the car {}", id);
        Car byVinNumber = carRepository.findByVinNumber(car.getVinNumber());
        byVinNumber.setPlateNumber(car.getPlateNumber());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> updatedBy = byVinNumber.getUpdatedBy();
        updatedBy.add(authentication.getName());

        return carRepository.save(byVinNumber);
    }

}
