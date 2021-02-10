package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.*;
import com.andrei.fleetManagement.service.*;
import com.andrei.fleetManagement.transfer.CreateContract;
import com.andrei.fleetManagement.transfer.CreateCustomer;
import com.andrei.fleetManagement.transfer.CreatePartner;
import com.andrei.fleetManagement.transfer.CreateUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/user")
public class UserController {

    private final UserService userService;
    private final ContractService contractService;
    private final CustomerService customerService;
    private final PartnerService partnerService;
    private final CarService carService;

    public UserController(UserService userService, ContractService contractService, CustomerService customerService, PartnerService partnerService, CarService carService) {
        this.userService = userService;
        this.contractService = contractService;
        this.customerService = customerService;
        this.partnerService = partnerService;
        this.carService = carService;
    }

    @GetMapping
    public User getCurrentUser(Principal principal){
        String name = principal.getName();
        return userService.getUserByName(name);
    }

    @GetMapping("/car")
    public Car getCarByPlateNumber(@RequestParam String plateNumber) {
        return carService.getCarByPlateNumber(plateNumber);
    }

    @GetMapping("/customer")
    public Customer getCustomer(@RequestParam String name) {
        return customerService.getCustomerByName(name);
    }

    @GetMapping("/partner")
    public Partner getPartner(@RequestParam String name) {
        return partnerService.getPartnerByName(name);
    }



    @PostMapping("/customer")
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

    @PostMapping("/partner")
    public Partner createPartner(@RequestBody CreatePartner createPartner) {

        CreateUser createUser = new CreateUser();
        createUser.setRole("PARTNER");
        createUser.setPermission("");
        createUser.setPassword(createPartner.getPassword());
        createUser.setEmail(createPartner.getEmail());
        createUser.setPhoneNumber(createPartner.getPhoneNumber());
        createUser.setUsername(createPartner.getEmail());
        User user = userService.createUser(createUser);

        return partnerService.createPartner(createPartner, user);
    }


}
