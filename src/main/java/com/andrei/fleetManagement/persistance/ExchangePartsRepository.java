package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.ExchangePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangePartsRepository extends JpaRepository<ExchangePart, Long> {

}
