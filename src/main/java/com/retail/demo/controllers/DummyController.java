package com.retail.demo.controllers;

import com.retail.demo.models.*;
import com.retail.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/")
public class DummyController {

    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String introduce() {
        return "<h1>This is a Spring Boot Application Test</h1>";
    }

    @GetMapping("/home")
    public String home() {
        return "<h1>This is the home page</h1>";
    }

    @GetMapping("/allProducts")
    public List<Product> getProducts() {
        return getProductService().getAllProducts();
    }
}
