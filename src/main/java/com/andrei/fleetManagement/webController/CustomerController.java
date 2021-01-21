package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Car;
import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Customer;
import com.andrei.fleetManagement.service.CarService;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.CustomerService;
import com.andrei.fleetManagement.transfer.CreateCar;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;
    private final ContractService contractService;

    public CustomerController(CustomerService customerService, CarService carService, ContractService contractService) {
        this.customerService = customerService;
        this.carService = carService;
        this.contractService = contractService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Car createCar(@RequestParam long customerId, @RequestBody CreateCar createCar) {
       return carService.createCar(customerId, createCar);
    }

    @GetMapping("/unfinishedContracts")
    public List<Contract> getUnfinishedContractsForCustomer(@RequestParam long id){
        return contractService.getUnfinishedContractsForCustomer(id);
    }


}
