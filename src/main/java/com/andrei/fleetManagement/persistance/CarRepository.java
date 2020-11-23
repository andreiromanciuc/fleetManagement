package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Override
    Page<Car> findAll(Pageable pageable);

    Car findByVinNumber (String vinNumber);

    Car findByPlateNumber (String plateNumber);
}
