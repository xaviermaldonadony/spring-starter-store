package com.example.store.repositories;

import com.example.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository  extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);
    // Select * from products where name = ?
}
