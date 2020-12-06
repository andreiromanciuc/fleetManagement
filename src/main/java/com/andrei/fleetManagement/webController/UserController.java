package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getCurrentUser(Principal principal){
        String name = principal.getName();
        return userService.getUserByName(name);
    }
}
