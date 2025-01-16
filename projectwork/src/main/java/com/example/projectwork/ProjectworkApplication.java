package com.example.projectwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectworkApplication.class, args);
    }
}