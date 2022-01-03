package com.retail.demo.controllers;

import com.retail.demo.models.Order;
import com.retail.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list-all")
    public List<Order> getOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getCustomerById(@PathVariable Integer id) {
        return this.orderService.findById(id);
    }

    @PostMapping("/add-order")
    public void addCustomer(@RequestBody Order order) {
        try {
            this.orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-order")
    public void updateCustomer(@RequestBody Order order) {
        try {
            this.orderService.update(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-order/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        try {
            this.orderService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
