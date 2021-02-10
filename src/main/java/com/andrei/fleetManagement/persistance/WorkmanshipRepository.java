package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Workmanship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkmanshipRepository extends JpaRepository<Workmanship, Long> {

    Workmanship findByName(String name);

}
