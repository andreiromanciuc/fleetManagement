package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.ExchangePart;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.ExchangePartRepository;
import com.andrei.fleetManagement.transfer.CreateExchangePart;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangePartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ExchangePartRepository exchangePartRepository;
    private final ContractService contractService;

    public ExchangePartService(ExchangePartRepository exchangePartRepository,
                               ContractService contractService) {
        this.exchangePartRepository = exchangePartRepository;
        this.contractService = contractService;
    }

    public ExchangePart createExchangePart(long contractId, CreateExchangePart createExchangePart) {
        LOGGER.info("Creating exchange part");

        Contract contract = contractService.getContractById(contractId);

        ExchangePart exchangePart = new ExchangePart();
        exchangePart.setCode(createExchangePart.getCode().toUpperCase());
        exchangePart.setName(createExchangePart.getName());
        exchangePart.setQuantity(createExchangePart.getQuantity());
        exchangePart.setPrice(createExchangePart.getPrice());
        exchangePart.setContract(contract);

        return exchangePartRepository.save(exchangePart);
    }

    public List<ExchangePart> getExchangePartByContract(Contract contract) {
        LOGGER.info("Retrieving exchange parts for contract");
        return exchangePartRepository.findByContract(contract);
    }

    public ExchangePart getExchangePartById(long id) {
        LOGGER.info("Retrieving exchange part {}", id);
        return exchangePartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Exchange part not found"));
    }

    public ExchangePart getExchangePartByName(String name) {
        LOGGER.info("Retrieving exchange part by name {}", name);
        return exchangePartRepository.findByName(name);
    }

    public ExchangePart updateExchangePart(CreateExchangePart createExchangePart, long id) {
        LOGGER.info("Updating exchange part {}", id);
        ExchangePart exchangePart = exchangePartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Exchange part not found"));
        exchangePart.setCode(createExchangePart.getCode());
        exchangePart.setName(createExchangePart.getName());
        exchangePart.setQuantity(createExchangePart.getQuantity());
        exchangePart.setPrice(createExchangePart.getPrice());

        return exchangePartRepository.save(exchangePart);
    }

    public void deleteExchangePart(long id) {
        LOGGER.info("Deleting exchange part {}", id);
        exchangePartRepository.deleteById(id);
    }
}
