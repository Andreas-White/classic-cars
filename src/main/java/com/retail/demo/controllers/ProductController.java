package com.retail.demo.controllers;

import com.retail.demo.models.Product;
import com.retail.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-all")
    public List<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/top-ten")
    public List<Product> getTopProducts() {
        return this.productService.getTopTenProducts();
    }

    @GetMapping("/bottom-ten")
    public List<Product> getBottomProducts() {
        return this.productService.getBottomTenProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return this.productService.getProductById(id);
    }

    @PostMapping("/add-product")
    public void addProduct(@RequestBody Product product) {
        try {
            this.productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-product")
    public void updateProduct(@RequestBody Product product) {
        try {
            this.productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable String id) {
        try {
            this.productService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
