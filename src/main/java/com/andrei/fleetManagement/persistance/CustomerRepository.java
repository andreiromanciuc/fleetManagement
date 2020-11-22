package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Page findAll(Pageable pageable);

    Page<Customer> findByName(Pageable pageable);


}
