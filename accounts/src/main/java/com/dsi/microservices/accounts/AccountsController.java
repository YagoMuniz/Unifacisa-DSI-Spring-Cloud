package com.dsi.microservices.accounts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountsController {

    @GetMapping("/")
    public String hello(){
        return "Hello Accounts!";
    }

}
