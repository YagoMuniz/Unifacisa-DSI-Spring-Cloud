package com.dsi.microservices.orders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @GetMapping("/")
    public String hello(){
        return "Hello Orders!";
    }

}
