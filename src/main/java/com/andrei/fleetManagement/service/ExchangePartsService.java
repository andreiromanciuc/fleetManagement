package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.ExchangePart;
import com.andrei.fleetManagement.persistance.ExchangePartsRepository;
import com.andrei.fleetManagement.transfer.CreateExchangePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExchangePartsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ExchangePartsRepository exchangePartsRepository;

    public ExchangePartsService(ExchangePartsRepository exchangePartsRepository) {
        this.exchangePartsRepository = exchangePartsRepository;
    }

    public ExchangePart createExchangePart(CreateExchangePart createExchangePart) {
        LOGGER.info("Creating exchange part");
        ExchangePart exchangePart = new ExchangePart();
        exchangePart.setCode(createExchangePart.getCode());
        exchangePart.setName(createExchangePart.getName());
        exchangePart.setQuantity(createExchangePart.getQuantity());
        exchangePart.setPrice(createExchangePart.getPrice());

        return exchangePartsRepository.save(exchangePart);
    }
}
