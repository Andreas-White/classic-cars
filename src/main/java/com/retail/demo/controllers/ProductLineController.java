package com.retail.demo.controllers;

import com.retail.demo.models.ProductLine;
import com.retail.demo.services.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product-line")
public class ProductLineController {

    private final ProductLineService service;

    @Autowired
    public ProductLineController(ProductLineService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public String getProductLines(Model model) {
        List<ProductLine> productLines = this.service.getAllProductLines();
        String title = "All ProductLines";

        model.addAttribute("productLines", productLines);
        model.addAttribute("title", title);
        return "/productLine/productLine-list";
    }

    @GetMapping("/{id}")
    public String getProductLinetById(Model model,
                                      @PathVariable String id) {
        ProductLine productLine = null;
        try {
            productLine = service.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No Product Line found with that ID");
        }
        model.addAttribute("productLine", productLine);
        return "/productLine/productLine";
    }

    @GetMapping("/add-product-line")
    public String addProductLine(Model model) {
        ProductLine productLine = new ProductLine();
        model.addAttribute("add", true);
        model.addAttribute("productLine", productLine);

        return "productLine/update";
    }

    @PostMapping("/add-product-line")
    public String processAddProductLine(Model model,
                                        @ModelAttribute("productLine") ProductLine productLine) {
        try {
            ProductLine newProductLine = service.save(productLine);
            return "redirect:/product-line/" + newProductLine.getProductLine();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/productLine/update";
        }
    }

    @GetMapping("/update-product-line/{id}")
    public String getUpdateProductLine(Model model,
                                       @PathVariable String id) {
        ProductLine productLine = null;
        try {
            productLine = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("productLine", productLine);
        return "/productLine/update";
    }

    @PostMapping("/update-product-line/{id}")
    public String processUpdateProductLine(Model model,
                                           @PathVariable String id,
                                           @ModelAttribute("productLine") ProductLine productLine) {
        try {
            productLine.setProductLine(id);
            service.update(productLine);
            return "redirect:/product-line/" + productLine.getProductLine();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/productLine/update";
        }
    }

    @GetMapping("/delete-product-line/{id}")
    public String getDeleteProductLine(Model model,
                                       @PathVariable String id) {
        ProductLine productLine = null;
        try {
            productLine = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("productLine", productLine);
        return "/productLine/productLine";
    }

    @PostMapping("/delete-product-line/{id}")
    public String deleteProductLine(Model model,
                                    @PathVariable String id) {
        try {
            service.deleteById(id);
            return "redirect:/product-line/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/productLine/productLine";
        }

    }


}
