package com.lsz.jys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ApplyApp {

    public static void main(String[] args) {
        SpringApplication.run(ApplyApp.class, args);
    }

}