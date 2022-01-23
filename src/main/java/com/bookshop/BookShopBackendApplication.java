package com.bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BookShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookShopBackendApplication.class, args);
    }

}
