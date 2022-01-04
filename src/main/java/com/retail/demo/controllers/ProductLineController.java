package com.retail.demo.controllers;

import com.retail.demo.models.ProductLine;
import com.retail.demo.services.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-line")
public class ProductLineController {

    private final ProductLineService service;

    @Autowired
    public ProductLineController(ProductLineService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public List<ProductLine> getProductLines() {
        return this.service.getAllProductLines();
    }

    @GetMapping("/{id}")
    public ProductLine getProductLinetById(@PathVariable String id) {
        return this.service.findById(id);
    }

    @PostMapping("/add-product-line")
    public void addProductLine(@RequestBody ProductLine productLine) {
        try {
            this.service.save(productLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-product-line")
    public void updateProductLine(@RequestBody ProductLine productLine) {
        try {
            this.service.update(productLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-product-line/{id}")
    public void deleteProductLine(@PathVariable String id) {
        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
