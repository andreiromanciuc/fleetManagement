package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Workmanship;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.WorkmanshipService;
import com.andrei.fleetManagement.transfer.CreateContract;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
    private final WorkmanshipService workmanshipService;

    public ContractController(ContractService contractService, WorkmanshipService workmanshipService) {
        this.contractService = contractService;
        this.workmanshipService = workmanshipService;
    }

    @PostMapping
    public Contract createContract(@RequestBody CreateContract createContract,
                                   @RequestParam long carId,
                                   @RequestParam long customerId,
                                   @RequestParam long partnerId) {
        return contractService.createContract(createContract, carId, customerId, partnerId);
    }

    @GetMapping
    public Contract getContract(@RequestParam long contractId) {
        return contractService.getContractById(contractId);
    }

    @GetMapping("/{contractId}")
    public List<Workmanship> getWorkmanshipByContract(@PathVariable long contractId) {
        Contract contract = contractService.getContractById(contractId);
        return workmanshipService.getWorkmanshipByContract(contract);
    }
}
