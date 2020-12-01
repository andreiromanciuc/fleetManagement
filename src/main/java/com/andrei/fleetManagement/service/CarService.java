package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Car;
import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.CarRepository;
import com.andrei.fleetManagement.transfer.CreateCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(CreateCar car) {
        LOGGER.info("Creating new car");
        Car newCar = new Car();
        newCar.setMileage(car.getMileage());
        newCar.setModel(car.getModel());
        newCar.setPlateNumber(car.getPlateNumber());
        newCar.setVinNumber(car.getVinNumber());

        return carRepository.save(newCar);
    }

    public Car addingContractToCar(long carId, Contract contract) {
        LOGGER.info("Adding contract to the car {}", carId);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This car was not found"));
        List<Contract> contractList = car.getContractList();
        contractList.add(contract);

        return car;
    }

    public Car getCarByPlateNumber(String plateNumber) {
        LOGGER.info("Retrieving car by plate number");
        Car car = carRepository.findByPlateNumber(plateNumber);
        return car;
    }

    public Car getCarByVin(String vinNumber) {
        LOGGER.info("Retrieving car by vin code");
        Car byVinNumber = carRepository.findByVinNumber(vinNumber);
        return byVinNumber;
    }

    public Car getCarById(long id) {
        LOGGER.info("Retrieving car by id {}", id);
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Car "+id+ " is not existing"));
    }

    public Car updateMileageOfTheCar(long id, CreateCar car){
        LOGGER.info("Updating mileage of the car {}", id);
        Car updatedCar = carRepository.findByVinNumber(car.getVinNumber());
        updatedCar.setMileage(car.getMileage());
        return carRepository.save(updatedCar);
    }

    public Car updatePlaceNumberOfTheCar(long id, CreateCar car){
        LOGGER.info("Updating plate number of the car {}", id);
        Car byVinNumber = carRepository.findByVinNumber(car.getVinNumber());
        byVinNumber.setPlateNumber(car.getPlateNumber());
        return carRepository.save(byVinNumber);
    }

}
