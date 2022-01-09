package com.retail.demo.controllers;


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
    public String addOrder(Model model,@ModelAttribute("order") Order order) {
        try {
            Order newOrder = orderService.save(order);
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
                                      @ModelAttribute("order") Order order) {
        try {
            order.setOrderNumber(id);
            orderService.update(order);
            return "redirect:/order/" + order.getOrderNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/order/update";
        }
    }


   // @PutMapping("/update-order")
   // public void updateCustomer(@RequestBody Order order) {
   //     try {
   //         this.orderService.update(order);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
//
   // @DeleteMapping("/delete-order/{id}")
   // public void deleteCustomer(@PathVariable Integer id) {
   //     try {
   //         this.orderService.deleteById(id);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
}
