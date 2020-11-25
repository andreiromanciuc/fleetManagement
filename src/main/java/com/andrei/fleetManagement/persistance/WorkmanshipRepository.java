package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Workmanship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkmanshipRepository extends JpaRepository<Workmanship, Long> {
}
