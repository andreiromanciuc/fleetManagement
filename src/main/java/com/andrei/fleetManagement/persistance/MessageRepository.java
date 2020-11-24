package com.andrei.fleetManagement.persistance;


import com.andrei.fleetManagement.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {


}
