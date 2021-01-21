package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Partner;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.PartnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/partner")
public class PartnerController {

    private final PartnerService partnerService;
    private final ContractService contractService;

    public PartnerController(PartnerService partnerService, ContractService contractService) {
        this.partnerService = partnerService;
        this.contractService = contractService;
    }

    @GetMapping
    public List<Partner> getPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/unfinishedContracts")
    public List<Contract> getUnfinishedContractsByPartner(@RequestParam long partnerId) {
        return contractService.getUnfinishedContractsForPartner(partnerId);
    }
}
