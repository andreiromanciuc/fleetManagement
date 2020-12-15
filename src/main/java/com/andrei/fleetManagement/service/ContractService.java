package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.*;
import com.andrei.fleetManagement.persistance.ContractRepository;
import com.andrei.fleetManagement.transfer.CreateContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;


@Service
public class ContractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ContractRepository contractRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final PartnerService partnerService;

    public ContractService(ContractRepository contractRepository,
                           CarService carService,
                           CustomerService customerService,
                           PartnerService partnerService) {
        this.contractRepository = contractRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.partnerService = partnerService;
    }

    public Contract createContract(CreateContract createContract,
                                   long carId,
                                   long customerId,
                                   long partnerId) {
        LOGGER.info("Creating contract");
        Partner partner = partnerService.getPartnerById(partnerId);
        Customer customer = customerService.getCustomerById(customerId);
        Car car = carService.getCarById(carId);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Bucharest"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Contract contract = new Contract(car, customer, partner);
        contract.setCar(car);
        contract.setCustomer(customer);
        contract.setPartner(partner);
        contract.setStartDate(day + "/" + month + "/" + year);
        contract.setFinished(false);
        contract.setOrderedParts(false);
        contract.setCreatedBy(Principal.class.getName());

        return contractRepository.save(contract);
    }

    public Contract updateCarOfTheContract(long contractId, long carId) {
        LOGGER.info("Updating contract by id {}", contractId);
        Contract contract = contractRepository.findById(contractId);
        Car car = carService.getCarById(carId);
        contract.setCar(car);

        List<String> updatedByList = contract.getUpdatedBy();
        updatedByList.add(Principal.class.getName());

        return contractRepository.save(contract);
    }

    public Contract updateContract(long contractId) {
        LOGGER.info("Updating contract by id {}", contractId);
        Contract contract = contractRepository.findById(contractId);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Bucharest"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        List<Object> updatedDateList = contract.getUpdatedDate();
        updatedDateList.add(day + "/" + month + "/" + year);

        List<String> updatedByList = contract.getUpdatedBy();
        updatedByList.add(Principal.class.getName());




        return contractRepository.save(contract);
    }

    public List<Contract> getUnfinishedContracts() {
        LOGGER.info("Retrieving all unfinished contracts");
        return contractRepository.findContractsByFinishedFalse();
    }

    public Contract getContractById(long id) {
        LOGGER.info("Retrieving contract {}", id);
        return contractRepository.findById(id);
    }

    public Contract setFixingAppointmentDate(long contractId, String date) {
        LOGGER.info("Set appointment date {}", date);

        Contract contract = getContractById(contractId);
        contract.setStartFixCarDate(date);

        return contract;
    }

    public Contract orderExchangePartsStatus(long id, String arrivalDate) {
        LOGGER.info("Set exchange parts status to ordered, and arrival date for last exchange part");

        Contract contract = getContractById(id);
        contract.setOrderedParts(true);
        contract.setArrivalPartsDate(arrivalDate);

        return contract;
    }

    public void deleteContract(long id) {
        LOGGER.info("Deleting contract {}", id);
        contractRepository.deleteById(id);
    }

}
