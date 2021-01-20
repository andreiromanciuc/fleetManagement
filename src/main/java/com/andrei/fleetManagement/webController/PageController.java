package com.andrei.fleetManagement.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("home")
    public String getHomeView() {
        return "home";
    }

    @GetMapping("user")
    public String getUserView() {
        return "user";
    }
}
