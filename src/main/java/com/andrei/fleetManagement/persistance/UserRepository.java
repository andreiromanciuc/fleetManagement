package com.andrei.fleetManagement.persistance;

import com.andrei.fleetManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
