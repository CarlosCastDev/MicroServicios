package com.carloscastillodeveloper.tienda.registro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicioRegistroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRegistroApplication.class, args);
	}

}
