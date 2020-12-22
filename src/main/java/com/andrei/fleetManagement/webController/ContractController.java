package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.ExchangePart;
import com.andrei.fleetManagement.domain.Workmanship;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.WorkmanshipService;
import com.andrei.fleetManagement.transfer.CreateWorkmanship;
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

    @GetMapping
    public Contract getContract(@RequestParam long contractId) {
        return contractService.getContractById(contractId);
    }

    @GetMapping("/{contractId}")
    public List<Workmanship> getWorkmanshipByContract(@PathVariable long contractId) {
        Contract contract = contractService.getContractById(contractId);
        return workmanshipService.getWorkmanshipByContract(contract);
    }

    @PutMapping("/{contractId}")
    public Contract addExchangePartToContract(@PathVariable long contractId, @RequestBody ExchangePart exchangePart) {
        return contractService.addExchangePartToContract(contractId, exchangePart);
    }

    @PutMapping
    public Contract addWorkmanshipToContract(@RequestParam long contractId,
                                             @RequestParam long partnerId,
                                             @RequestParam String code,
                                             @RequestParam String name,
                                             @RequestParam double timing,
                                             @RequestParam double price) {

        CreateWorkmanship createWorkmanship = new CreateWorkmanship();
        createWorkmanship.setCode(code);
        createWorkmanship.setName(name);
        createWorkmanship.setTiming(timing);
        createWorkmanship.setPrice(price);

        return contractService.addWorkmanshipToContract(contractId,
                workmanshipService.createWorkmanship(partnerId, createWorkmanship));
    }
}
