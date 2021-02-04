package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findContractsByFinishedFalse();


}
