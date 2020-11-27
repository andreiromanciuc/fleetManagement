package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.*;
import com.andrei.fleetManagement.persistance.ContractRepository;
import com.andrei.fleetManagement.transfer.CreateContract;
import com.andrei.fleetManagement.transfer.CreateExchangePart;
import com.andrei.fleetManagement.transfer.CreateWorkmanship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
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
        int month = calendar.get(Calendar.MONTH + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Contract contract = new Contract(car, customer, partner);
        contract.setCar(car);
        contract.setCustomer(customer);
        contract.setPartner(partner);
        contract.setStartDate(day + "/"+ month +"/"+ year);

        return contractRepository.save(contract);
    }

    public Contract addingPartsToContract(long contractId,
                                          ExchangePart exchangePart) {
        LOGGER.info("Adding info to contract {}", contractId);

        Contract contract = contractRepository.findById(contractId);

        List<ExchangePart> exchangePartsList = new ArrayList<>();
        exchangePartsList.add(exchangePart);

        return contract;
    }

    public Contract addingWorkToContract(long contractId,
                                         Workmanship workmanship) {
        LOGGER.info("Adding info to contract {}", contractId);

        Contract contract = contractRepository.findById(contractId);

        List<Workmanship> workmanshipList = new ArrayList<>();
        workmanshipList.add(workmanship);

        return contract;
    }

    public void deleteContract(long id) {
        LOGGER.info("Deleting contract {}", id);
        contractRepository.deleteById(id);
    }

}