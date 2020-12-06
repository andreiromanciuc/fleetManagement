package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Partner;
import com.andrei.fleetManagement.service.PartnerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<Partner> getPartners() {
        return partnerService.getAllPartners();
    }
}
