package com.linkjb;

import com.linkjb.conf.AsyncConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCaching
@EnableScheduling//支持schedule
public class LinkDiskApplication {
    private static Logger log = LoggerFactory.getLogger(LinkDiskApplication.class) ;
    public static void main(String[] args) {
        SpringApplication.run(LinkDiskApplication.class, args);
    }

}

