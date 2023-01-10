package com.example.store;

import com.example.store.domain.Product;
import com.example.store.domain.User;
import com.example.store.service.ProductsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class StoreApplication {

    public static void main(String[] args){
        SpringApplication.run(StoreApplication.class, args);
    }

}
