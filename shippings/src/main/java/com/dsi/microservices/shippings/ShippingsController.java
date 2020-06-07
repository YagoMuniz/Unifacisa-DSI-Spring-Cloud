package com.dsi.microservices.shippings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shippings")
public class ShippingsController {

    @GetMapping("/")
    public String hello(){
        return "Hello Shippings!";
    }

}
