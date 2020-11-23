package com.andrei.fleetManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @GetMapping
    public String returnService() {
        String a = "You are costume";
        return a;
    }
}
