package com.retail.demo.controllers;

import com.retail.demo.models.Customer;
import com.retail.demo.models.Product;
import com.retail.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-all")
    public String getProducts(Model model) {
        List<Product> products = this.productService.getAllProducts();
        String title = "All Products";

        model.addAttribute("products", products);
        model.addAttribute("title", title);
        return "/product/product-list";
    }

    @GetMapping("/top-ten")
    public String getTopTen(Model model) {
        List<Product> products = this.productService.getTopTenProducts();
        String title = "All Products";

        model.addAttribute("products", products);
        model.addAttribute("title", title);
        return "/product/product-list";
    }
    @GetMapping("/bottom-ten")
    public String getBottomTen(Model model) {
        List<Product> products = this.productService.getBottomTenProducts();
        String title = "All Products";

        model.addAttribute("products", products);
        model.addAttribute("title", title);
        return "/product/product-list";
    }
    @GetMapping("/{id}")
    public String getProductById(Model model,
                                  @PathVariable String id) {
        Product product = null;
        try {
            product  = productService.getProductById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No customer found with that ID");
        }
        model.addAttribute("product", product);
        return "/product/product";
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
