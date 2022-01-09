package com.retail.demo.controllers;


import com.retail.demo.models.Order;
import com.retail.demo.models.OrderDT;
import com.retail.demo.models.Payment;
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
        return "/order/order-list";
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

    @GetMapping("/add-order")
    public String getAddOrder(Model model) {
        Order order = new Order();
        model.addAttribute("add", true);
        model.addAttribute("order", order);

        return "order/update";
    }

    @PostMapping("/add-order")
    public String addOrder(Model model,@ModelAttribute("order") OrderDT orderDT) {
        try {
            Order newOrder1 = new Order();
            newOrder1.setOrderNumber(orderDT.getOrderNumber());
            newOrder1.setOrderDate(orderService.convert(orderDT.getOrderDate()));
            newOrder1.setCustomerNumber(orderDT.getCustomerNumber());
            newOrder1.setComments(orderDT.getComments());
            newOrder1.setRequiredDate(orderService.convert(orderDT.getRequiredDate()));
            newOrder1.setShippedDate(orderService.convert(orderDT.getShippedDate()));
            newOrder1.setStatus(orderDT.getStatus());


            Order newOrder = orderService.save(newOrder1);
            return "redirect:/order/" + newOrder.getOrderNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/order/update";
        }
    }

    @GetMapping("/update-order/{id}")
    public String getUpdateOrder(Model model, @PathVariable Integer id) {
        Order order = null;
        try {
            order = orderService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("order", order);
        return "/order/update";
    }

    @PostMapping("/update-order/{id}")
    public String processUpdateOrder(Model model,
                                      @PathVariable Integer id,
                                      @ModelAttribute("order") OrderDT orderDT) {
        try {
            Order newOrder1 = new Order();
            newOrder1.setOrderNumber(id);
            newOrder1.setOrderDate(orderService.convert(orderDT.getOrderDate()));
            newOrder1.setCustomerNumber(orderDT.getCustomerNumber());
            newOrder1.setComments(orderDT.getComments());
            newOrder1.setRequiredDate(orderService.convert(orderDT.getRequiredDate()));
            newOrder1.setShippedDate(orderService.convert(orderDT.getShippedDate()));
            newOrder1.setStatus(orderDT.getStatus());
            orderService.update(newOrder1);
            return "redirect:/order/" + newOrder1.getOrderNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/order/update";
        }
    }

    @GetMapping("/delete-order/{id}")
    public String getDeleteOrder(Model model,
                                   @PathVariable Integer id) {
        Order order = null;
        try {
            order = orderService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("order", order);
        return "/order/order";
    }

    @PostMapping("/delete-order/{id}")
    public String deleteOrder(Model model,
                                @PathVariable Integer id) {
        try {
            orderService.deleteById(id);
            return "redirect:/order/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/order/order";
        }
    }
    
}
