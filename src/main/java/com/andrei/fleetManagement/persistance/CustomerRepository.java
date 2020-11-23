package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByName(String name);

}
