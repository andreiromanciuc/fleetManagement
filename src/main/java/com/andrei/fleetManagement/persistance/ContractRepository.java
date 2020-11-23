package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findById (long id);

}
