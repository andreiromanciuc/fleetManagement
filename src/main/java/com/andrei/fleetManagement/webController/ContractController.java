package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.ExchangePart;
import com.andrei.fleetManagement.domain.Workmanship;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.ExchangePartService;
import com.andrei.fleetManagement.service.WorkmanshipService;
import com.andrei.fleetManagement.transfer.CreateContract;
import com.andrei.fleetManagement.transfer.CreateExchangePart;
import com.andrei.fleetManagement.transfer.CreateWorkmanship;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/user/contract")
public class ContractController {

    private final ContractService contractService;
    private final WorkmanshipService workmanshipService;
    private final ExchangePartService exchangePartService;

    public ContractController(ContractService contractService, WorkmanshipService workmanshipService, ExchangePartService exchangePartService) {
        this.contractService = contractService;
        this.workmanshipService = workmanshipService;
        this.exchangePartService = exchangePartService;
    }

    @PostMapping("/workmanship/{contractId}")
    public Contract createWorkmanship(@PathVariable long contractId, @RequestBody CreateWorkmanship createWorkmanship) {
        Workmanship workmanship = workmanshipService.createWorkmanship(contractId, createWorkmanship);
        return contractService.addWorkmanshipToContract(contractId, workmanship);
    }

    @PostMapping("/exchangePart/{contractId}")
    public Contract createExchangePart(@PathVariable long contractId, @RequestBody CreateExchangePart createExchangePart) {
        ExchangePart exchangePart = exchangePartService.createExchangePart(contractId, createExchangePart);
        return contractService.addExchangePartToContract(contractId, exchangePart);
    }

    @PostMapping
    public Contract createContract(@RequestBody CreateContract createContract){
        return contractService.createContract(createContract);
    }

    @GetMapping("/contracts")
    public List<Contract> getUnfinishedContractsByUser() {
        return contractService.getUnfinishedContractsByUser();
    }

    @GetMapping("/{contractId}")
    public Contract getContract(@PathVariable long contractId) {
        return contractService.getContractById(contractId);
    }





}
