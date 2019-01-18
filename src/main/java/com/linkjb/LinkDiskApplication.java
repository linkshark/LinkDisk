package com.linkjb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling//支持schedule
public class LinkDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkDiskApplication.class, args);
    }

}

