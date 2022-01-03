package com.retail.demo.controllers;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {

    private final OrderDetailsService service;

    @Autowired
    public OrderDetailsController(OrderDetailsService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public List<OrderDetails> getOrderDetails() {
        return this.service.getAllOrderDetails();
    }

    @GetMapping("/list-order-number/{number}")
    public List<OrderDetails> getOrderDetailsFromOrderNumber(@PathVariable Integer number) {
        return this.service.getAllOrderDetailsByOrderNumber(number);
    }

    @GetMapping("/list-product-code/{code}")
    public List<OrderDetails> getOrderDetailsFromProductCode(@PathVariable String code) {
        return this.service.getAllOrderDetailsByProductCode(code);
    }

    @GetMapping("/id")
    public OrderDetails getOrderDetailsById(@RequestParam Integer number,
                                           @RequestParam String code) {
        return this.service.findById(number, code);
    }

    @PostMapping("/add-order-details")
    public void addOrderDetails(@RequestBody OrderDetails orderDetails) {
        try {
            this.service.save(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-order-details")
    public void updateOrderDetails(@RequestBody OrderDetails orderDetails) {
        try {
            this.service.update(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-order-details")
    public void deleteOrderDetails(@RequestParam Integer number,
                                   @RequestParam String code) {
        try {
            this.service.deleteById(number, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
