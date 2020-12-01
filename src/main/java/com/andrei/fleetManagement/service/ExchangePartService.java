package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.ExchangePart;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.ExchangePartRepository;
import com.andrei.fleetManagement.transfer.CreateExchangePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public ExchangePart createExchangePart(long id, CreateExchangePart createExchangePart) {
        LOGGER.info("Creating exchange part");
        ExchangePart exchangePart = new ExchangePart();
        exchangePart.setCode(createExchangePart.getCode());
        exchangePart.setName(createExchangePart.getName());
        exchangePart.setQuantity(createExchangePart.getQuantity());
        exchangePart.setPrice(createExchangePart.getPrice());

        return exchangePartRepository.save(exchangePart);
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
