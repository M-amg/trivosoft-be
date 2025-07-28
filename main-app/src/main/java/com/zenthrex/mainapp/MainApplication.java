package com.zenthrex.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zenthrex.*"})
@EntityScan(basePackages = {"com.zenthrex.*"})
@EnableJpaRepositories(basePackages = {"com.zenthrex.*"})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.
                run(MainApplication.class, args);
    }

}
