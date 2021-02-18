package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.*;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.ContractRepository;
import com.andrei.fleetManagement.transfer.CreateContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ContractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ContractRepository contractRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final PartnerService partnerService;
    private final UserService userService;

    public ContractService(ContractRepository contractRepository,
                           CarService carService,
                           CustomerService customerService,
                           PartnerService partnerService, UserService userService) {
        this.contractRepository = contractRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.partnerService = partnerService;
        this.userService = userService;
    }

    public Contract createContract(CreateContract createContract) {
        LOGGER.info("Creating contract");
        Partner partner = partnerService.getPartnerById(createContract.getPartnerId());
        Customer customer = customerService.getCustomerById(createContract.getCustomerId());
        Car car = carService.getCarById(createContract.getCarId());

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
        contract.setStartDate(day + "." + month + "." + year);
        contract.setFinished(false);
        contract.setOrderedParts(false);
        contract.setBranch(createContract.getBranch().toUpperCase());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        contract.setCreatedBy(authentication.getName());

        Contract newContract = contractRepository.save(contract);

        LOGGER.info("Contract was created with id {}", newContract.getId());
        return newContract;
    }

    public Contract addExchangePartToContract(long contractId, ExchangePart exchangePart) {
        LOGGER.info("Adding exchange part to contract {}", contractId);
        Contract contract = getContractById(contractId);
        List<ExchangePart> exchangePartList = contract.getExchangePartList();
        exchangePartList.add(exchangePart);

        return contractRepository.save(contract);
    }

    public Contract addWorkmanshipToContract(long contractId, Workmanship workmanship) {
        LOGGER.info("Adding workmanship to contract {}", contractId);
        Contract contract = getContractById(contractId);
        List<Workmanship> workmanshipList = contract.getWorkmanshipList();
        workmanshipList.add(workmanship);

        return contractRepository.save(contract);
    }
    public Contract updateCarOfTheContract(long contractId, long carId) {
        LOGGER.info("Updating contract by id {}", contractId);
        Contract contract = getContractById(contractId);
        Car car = carService.getCarById(carId);
        contract.setCar(car);

        List<String> updatedByList = contract.getUpdatedBy();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        updatedByList.add(authentication.getName());

        return contractRepository.save(contract);
    }

    public Contract updateContract(long contractId) {
        LOGGER.info("Updating contract by id {}", contractId);
        Contract contract = getContractById(contractId);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Bucharest"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = day + "." + month + "." + + year;

        List<String> updatedDateList = contract.getUpdatedDate();
        updatedDateList.add(dateString);

        List<String> updatedByList = contract.getUpdatedBy();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        updatedByList.add(authentication.getName());

        return contractRepository.save(contract);
    }

    public List<Contract> getUnfinishedContractsByUser() {
        LOGGER.info("Retrieving unfinished contracts by user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return contractRepository.findContractsByCreatedBy(authentication.getName());
    }

    public List<Contract> getUnfinishedContractsForCustomer(long customerId) {
        LOGGER.info("Retrieving unfinished contracts for customer {}", customerId);
        List<Contract> contractsByFinishedFalse = contractRepository.findContractsByFinishedFalse();

        List<Contract> finalList = new ArrayList<>();
        for (Contract contract : contractsByFinishedFalse) {
            if (contract.getCustomer().getId() == customerId) {
                finalList.add(contract);
            }
        }

        return finalList;
    }

    public List<Contract> getUnfinishedContractsForPartner(long partnerId) {
        LOGGER.info("Retrieving unfinished contracts for partner {}", partnerId);
        List<Contract> contractsByFinishedFalse = contractRepository.findContractsByFinishedFalse();

        List<Contract> finalList = new ArrayList<>();
        for (Contract contract : contractsByFinishedFalse) {
            if (contract.getPartner().getId() == partnerId) {
                finalList.add(contract);
            }
        }
        return finalList;
    }

    public Contract getContractById(long id) {
        LOGGER.info("Retrieving contract {}", id);
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This contract " + id + " does not exist"));
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
