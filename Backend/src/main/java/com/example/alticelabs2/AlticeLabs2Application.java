package com.example.alticelabs2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "LabSec API Documentation",version = "1.0.1", description = "LabSec API Documentation")
)
public class AlticeLabs2Application {

    public static void main(String[] args) {

        SpringApplication.run(AlticeLabs2Application.class, args);
    }

}

