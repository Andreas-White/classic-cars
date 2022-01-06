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

    public Product getProductById(String id) { return  this.repository.findById(id).orElse(null); }

    public List<Product> getTopTenProducts() {
        return new ArrayList<>(repository.getTopTenProducts());
    }

    public List<Product> getBottomTenProducts() {
        return new ArrayList<>(repository.getBottomTenProducts());
    }

    public void save(Product product) throws Exception {

        if (product.getProductCode() == null) {
            String id = this.repository.getMaxId();
            String numPart = String.valueOf(Integer.parseInt(id.substring(4)) + 3);
            String newId = id.substring(0,4).concat(numPart);
            product.setProductCode(newId);
        }

        if (existsById(product.getProductCode())) {
            throw new Exception("Payment: " + product.getProductName() + " already exists");
        }
        this.repository.save(product);
    }

    public void update(Product product) throws Exception {

        if (!existsById(product.getProductCode())) {
            throw new Exception("Cannot find product with id: " + product.getProductCode());
        }

        if (product.getProductCode() == null) {
            throw new Exception("There was no id for product: " + product.getProductCode());
        }
        this.repository.save(product);
    }

    public void deleteById(String id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find customer with id: " + id);
        }
        else {
            this.repository.deleteById(id);
        }
    }

    public Long count() {
        return this.repository.count();
    }

}
