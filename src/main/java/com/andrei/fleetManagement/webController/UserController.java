package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.service.ContractService;
import com.andrei.fleetManagement.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("home/user")
public class UserController {

    private final UserService userService;
    private final ContractService contractService;

    public UserController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }

    @GetMapping
    public User getCurrentUser(Principal principal){
        String name = principal.getName();
        return userService.getUserByName(name);
    }

    @GetMapping("/contracts")
    public List<Contract> getUnfinishedContracts() {
        return contractService.getUnfinishedContracts();
    }
}
