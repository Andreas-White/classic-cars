package com.retail.demo.services;

import com.retail.demo.models.Product;
import com.retail.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(repository.findAll());
    }

    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

}
