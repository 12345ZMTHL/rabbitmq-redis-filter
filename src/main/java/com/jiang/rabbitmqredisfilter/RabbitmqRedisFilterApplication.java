package com.jiang.rabbitmqredisfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.jiang")
public class RabbitmqRedisFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqRedisFilterApplication.class, args);
    }

}
