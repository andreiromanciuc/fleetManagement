package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.transfer.CreateContract;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public Contract createContract(@RequestBody CreateContract createContract,
                                   @RequestParam long carId,
                                   @RequestParam long customerId,
                                   @RequestParam long partnerId) {
        return contractService.createContract(createContract, carId, customerId, partnerId);
    }
}
