package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Car;
import com.andrei.fleetManagement.domain.Customer;
import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.service.CarService;
import com.andrei.fleetManagement.service.CustomerService;
import com.andrei.fleetManagement.service.UserService;
import com.andrei.fleetManagement.transfer.CreateCar;
import com.andrei.fleetManagement.transfer.CreateCustomer;
import com.andrei.fleetManagement.transfer.CreateUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;

    public CustomerController(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Car createCar(@RequestParam long customerId, @RequestBody CreateCar createCar) {
       return carService.createCar(customerId, createCar);
    }



}
