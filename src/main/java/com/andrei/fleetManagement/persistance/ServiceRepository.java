package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Customer;
import com.andrei.fleetManagement.domain.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Override
    Page<Service> findAll(Pageable pageable);
    Page<Service> findByName (Pageable pageable);

}
