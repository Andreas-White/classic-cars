package com.retail.demo.services;

import com.retail.demo.models.ProductLine;
import com.retail.demo.repositories.ProductLineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLineService {

    private ProductLineRepository repository;

    public ProductLineService(ProductLineRepository repository) {
        this.repository = repository;
    }

    public List<ProductLine> getAllProductLines() {
        return new ArrayList<>(repository.findAll());
    }
}
