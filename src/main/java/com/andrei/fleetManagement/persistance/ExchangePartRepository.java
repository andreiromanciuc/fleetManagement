package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.ExchangePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangePartRepository extends JpaRepository<ExchangePart, Long> {

    ExchangePart findByName (String name);

    List<ExchangePart> findByContract(Contract contract);
}
