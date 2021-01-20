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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;
    private final UserService userService;

    public CustomerController(CustomerService customerService, CarService carService, UserService userService) {
        this.customerService = customerService;
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/{customerId}")
    public Car createCar(@PathVariable long customerId, @RequestBody CreateCar createCar) {
       return carService.createCar(customerId, createCar);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomer createCustomer) {

        CreateUser createUser = new CreateUser();
        createUser.setRole("CUSTOMER");
        createUser.setPermission("");
        createUser.setPassword(createCustomer.getPassword());
        createUser.setEmail(createCustomer.getEmail());
        createUser.setPhoneNumber(createCustomer.getPhoneNumber());
        createUser.setUsername(createCustomer.getEmail());
        User user = userService.createUser(createUser);

        return customerService.createCustomer(createCustomer, user);
    }

}
