package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.ExchangePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangePartRepository extends JpaRepository<ExchangePart, Long> {

    ExchangePart findByName (String name);
}
