package com.linkjb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LinkDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkDiskApplication.class, args);
    }

}

