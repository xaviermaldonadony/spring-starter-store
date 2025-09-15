package com.example.store;

import com.example.store.entities.User;
import com.example.store.repositories.UserRepository;
import com.example.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context  =  SpringApplication.run(StoreApplication.class, args);
        var repository = context.getBean(UserService.class);

        repository.fetchSortedProducts();
        repository.fetchPaginatedProducts(0, 10);
    }
}
// 4.9.6