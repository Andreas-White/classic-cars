package com.retail.demo.controllers;

import com.retail.demo.models.Office;
import com.retail.demo.models.Order;
import com.retail.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list-all")
    public String getOrders(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        String title = "All Orders";

        model.addAttribute("orders", orders);
        model.addAttribute("title", title);
        return "/Order/Order-list";
    }

    @GetMapping("/{id}")
    public String getOrderById(Model model,@PathVariable Integer id) {
        Order order = null;
        try {
            order  = orderService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No orders found with that ID");
        }
        model.addAttribute("order", order);
        return "/order/order";
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
