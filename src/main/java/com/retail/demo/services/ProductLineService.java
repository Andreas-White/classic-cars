package com.retail.demo.services;

import com.retail.demo.models.ProductLine;
import com.retail.demo.repositories.ProductLineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLineService {

    private final ProductLineRepository repository;

    public ProductLineService(ProductLineRepository repository) {
        this.repository = repository;
    }

    public List<ProductLine> getAllProductLines() {
        return new ArrayList<>(repository.findAll());
    }

    private boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    public ProductLine findById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    public void save(ProductLine productLine) throws Exception {

        if (productLine.getProductLine() == null) {
            throw new Exception("You must provide a name for product line");
        }

        if (existsById(productLine.getProductLine())) {
            throw new Exception("Product line with id:" + productLine.getProductLine() + " already exists");
        }
        this.repository.save(productLine);
    }

    public void update(ProductLine productLine) throws Exception {

        if (!existsById(productLine.getProductLine())) {
            throw new Exception("Cannot find productLine with id: " + productLine.getProductLine());
        }

        if (productLine.getProductLine() == null) {
            throw new Exception("There was no id for productLine: " + productLine.getProductLine());
        }
        this.repository.save(productLine);
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
