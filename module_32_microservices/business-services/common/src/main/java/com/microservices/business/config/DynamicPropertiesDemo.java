package com.microservices.business.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DynamicPropertiesDemo {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicPropertiesDemo.class);

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DynamicPropertiesDemo.class, args);
    }
}
