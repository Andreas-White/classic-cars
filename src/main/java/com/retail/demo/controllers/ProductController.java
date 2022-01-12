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
        String title = "Top 10 Products";

        model.addAttribute("products", products);
        model.addAttribute("title", title);
        return "/product/product-list";
    }
    @GetMapping("/bottom-ten")
    public String getBottomTen(Model model) {
        List<Product> products = this.productService.getBottomTenProducts();
        String title = "Bottom 10 Products";

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
    @GetMapping("/add-product")
    public String getAddProduct(Model model) {
        Product product = new Product();
        model.addAttribute("add", true);
        model.addAttribute("product", product);

        return "product/update";
    }

    @PostMapping("/add-product")
    public String processAddProduct(Model model,
                                     @ModelAttribute("product") Product product) {
        try {
            Product newProduct = productService.save(product);
            return "redirect:/product/" + newProduct.getProductCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/product/update";
        }
    }

    @GetMapping("/update-product/{id}")
    public String getUpdateProduct(Model model, @PathVariable String id) {
        Product product = null;
        try {
            product = productService.getProductById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("product", product);
        return "/product/update";
    }

    @PostMapping("/update-product/{id}")
    public String processUpdateProduct(Model model,
                                        @PathVariable String id,
                                        @ModelAttribute("product") Product product) {
        try {
            product.setProductCode(id);
            productService.update(product);
            return "redirect:/product/" + product.getProductCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/product/update";
        }
    }

    @GetMapping("/delete-product/{id}")
    public String getDeleteProduct(Model model,
                                    @PathVariable String id) {
        Product product = null;
        try {
            product = productService.getProductById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("product", product);
        return "/product/product";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(Model model,
                                 @PathVariable String id) {
        try {
            productService.deleteById(id);
            return "redirect:/product/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/product/product";
        }
    }
 // @PutMapping("/update-product")
 // public void updateProduct(@RequestBody Product product) {
 //     try {
 //         this.productService.update(product);
 //     } catch (Exception e) {
 //         e.printStackTrace();
 //     }
 // }

 // @DeleteMapping("/delete-product/{id}")
 // public void deleteProduct(@PathVariable String id) {
 //     try {
 //         this.productService.deleteById(id);
 //     } catch (Exception e) {
 //         e.printStackTrace();
 //     }
 // }
}
