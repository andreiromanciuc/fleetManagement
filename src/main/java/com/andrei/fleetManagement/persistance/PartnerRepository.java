package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Partner findByName (String name);

}
