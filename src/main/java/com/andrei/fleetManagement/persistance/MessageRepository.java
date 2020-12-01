package com.andrei.fleetManagement.persistance;


import com.andrei.fleetManagement.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByContract(Long id);
}
