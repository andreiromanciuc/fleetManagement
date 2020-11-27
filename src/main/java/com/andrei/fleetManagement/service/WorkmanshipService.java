package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Workmanship;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.WorkmanshipRepository;
import com.andrei.fleetManagement.transfer.CreateWorkmanship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkmanshipService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final WorkmanshipRepository workmanshipRepository;

    public WorkmanshipService(WorkmanshipRepository workmanshipRepository) {
        this.workmanshipRepository = workmanshipRepository;
    }

    public Workmanship createWorkmanship(CreateWorkmanship createWorkmanship) {
        LOGGER.info("Creating new workmanship");
        Workmanship workmanship = new Workmanship();
        workmanship.setCode(createWorkmanship.getCode());
        workmanship.setName(createWorkmanship.getName());
        workmanship.setTiming(createWorkmanship.getTiming());
        workmanship.setPrice(createWorkmanship.getPrice());

        return workmanshipRepository.save(workmanship);
    }

    public Workmanship updateWorkmanship(CreateWorkmanship createWorkmanship, long id) {
        LOGGER.info("Updating workmanship with id {}", id);
        Workmanship workmanship = workmanshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This workmanship was not found"));
        workmanship.setCode(createWorkmanship.getCode());
        workmanship.setName(createWorkmanship.getName());
        workmanship.setTiming(createWorkmanship.getTiming());
        workmanship.setPrice(createWorkmanship.getPrice());

        return workmanshipRepository.save(workmanship);
    }

    public Workmanship getWorkmanship(long id) {
        LOGGER.info("Retrieving workmanship {}", id);
        return workmanshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Workmanship not found"));
    }

    public Workmanship getWorkmanshipByName(String name) {
        LOGGER.info("Retrieving workmanship {}", name);
        return workmanshipRepository.findByName(name);
    }

//    public Workmanship getWorkmanshipByContractId(long contractId) {
//        LOGGER.info("Retrieving workmanship by contract {}", contractId);
//
//    }

    public void deleteWorkmanship(long id) {
        LOGGER.info("Deleting workmanship {}", id);
        workmanshipRepository.deleteById(id);
    }

}
