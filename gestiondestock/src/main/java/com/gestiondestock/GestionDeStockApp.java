package com.gestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionDeStockApp {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeStockApp.class, args);
        System.out.println("Application Gestion de stock started");
    }

}
