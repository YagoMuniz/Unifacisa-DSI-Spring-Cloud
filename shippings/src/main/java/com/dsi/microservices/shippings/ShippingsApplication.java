package com.dsi.microservices.shippings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShippingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingsApplication.class, args);
	}

}
