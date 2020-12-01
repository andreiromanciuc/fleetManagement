package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Partner;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.PartnerRepository;
import com.andrei.fleetManagement.transfer.CreatePartner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Partner createPartner(CreatePartner createPartner) {
        LOGGER.info("Creating partner");
        Partner partner = new Partner();
        partner.setName(createPartner.getName());
        partner.setEmail(createPartner.getEmail());
        partner.setPassword(createPartner.getPassword());
        partner.setPhoneNumber(createPartner.getPhoneNumber());
        partner.setCif(createPartner.getCif());
        partner.setBankAccount(createPartner.getBankAccount());
        partner.setAddress(createPartner.getAddress());
        partner.setContactPerson(createPartner.getContactPerson());
        return partnerRepository.save(partner);
    }

    public Partner addingContractToPartner(long partnerId, Contract contract) {
        LOGGER.info("Adding contract to partner {}", partnerId);
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This partner was not found"));
        List<Contract> contractList = partner.getContractList();
        contractList.add(contract);

        return partner;
    }

    public void deletePartner(long id) {
        LOGGER.info("Deleting partner");
        partnerRepository.deleteById(id);
    }

    public Partner getPartnerByName(String name) {
        LOGGER.info("Retrieving partner {}", name);
         return partnerRepository.findByName(name);
    }

    public Partner getPartnerById(long id) {
        LOGGER.info("Retrieving partner by id {}", id);
        return partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Partner " + id + " is not found"));
    }
}
