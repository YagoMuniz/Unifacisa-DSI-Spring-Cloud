package com.dsi.microservices.inventories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventories")
public class InventoriesController {

    @GetMapping("/")
    public String hello(){
        return "Hello Inventories!";
    }

}
