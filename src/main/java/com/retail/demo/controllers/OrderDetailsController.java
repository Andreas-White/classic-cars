package com.retail.demo.controllers;

import com.retail.demo.models.Employee;
import com.retail.demo.models.Office;
import com.retail.demo.models.OrderDetails;
import com.retail.demo.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order-details")
public class OrderDetailsController {

    private final OrderDetailsService service;

    @Autowired
    public OrderDetailsController(OrderDetailsService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public String getAllOrderDetails(Model model) {
        List<OrderDetails> orderDetails = this.service.getAllOrderDetails();
        String title = "All Order Details List";

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("title", title);
        return "/orderDetails/order-details-list";
    }

    @GetMapping("/list-order-number/{number}")
    public String getOrderDetailsFromOrderNumber(Model model,
                                                 @PathVariable Integer id) {

        List<OrderDetails> orderDetails = this.service.getAllOrderDetailsByOrderNumber(id);
        String title = "All OrderDetails from order: " + id;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("orderDetails", orderDetails);

        return "/orderDetails/orderDetails-list";
    }

    @GetMapping("/list-product-code/{code}")
    public String getOrderDetailsFromProductCode(Model model,
                                                             @PathVariable String id) {

        List<OrderDetails> orderDetails = this.service.getAllOrderDetailsByProductCode(id);
        String title = "All OrderDetails containing product with code: " + id;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("orderDetails", orderDetails);

        return "/orderDetails/orderDetails-list";
    }

    @GetMapping("/id")
    public String getOrderDetailsById(Model model,@PathVariable Integer number,@PathVariable String code) {
        OrderDetails orderDetails = null;
        try {
            orderDetails  = service.findById(number,code);                          //////////!!!
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No order Details found with that ID");
        }
        model.addAttribute("orderDetails", orderDetails);
        return "/orderDetails/orderDetails";
    }

    @GetMapping("/add-order-details")
    public String getAddOrderDetails(Model model) {
        OrderDetails orderDetails = new OrderDetails();
        model.addAttribute("add", true);
        model.addAttribute("orderDetails", orderDetails);

        return "orderDetails/update";
    }


    @PostMapping("/add-order-details")
    public String addOrderDetails(Model model,@ModelAttribute("office") OrderDetails orderDetails) {
        try {
            OrderDetails newOrderDetails = service.save(orderDetails);
            return "redirect:/orderDetails/" + newOrderDetails.getOrderNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/orderDetails/update";
        }
    }

    @GetMapping("/update-order-details/{id}")
    public String getUpdateOrderDetails(Model model, @PathVariable Integer number,
                                              @PathVariable String code ) {
        OrderDetails orderDetails = null;
        try {
            orderDetails = service.findById(number,code);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("orderDetails", orderDetails);
        return "/orderDetails/update";
    }

    @PostMapping("/update-order-details/{id}")
    public String processUpdateOrderDetails(Model model,
                                      @PathVariable Integer number,
                                      @PathVariable String code ,
                                      @ModelAttribute("orderDetails") OrderDetails orderDetails) {
        try {
            orderDetails.setOrderNumber(number);
            service.update(orderDetails);
            return "redirect:/orderDetails/" + orderDetails.getOrderNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/orderDetails/update";
        }
    }

    @GetMapping("/delete-order-details/{id}")
    public String getDeleteOrderDetails(Model model,
                                  @PathVariable Integer number,
                                        @PathVariable String code) {

        OrderDetails orderDetails = null;
        try {
            orderDetails = service.findById(number,code);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("orderDetails", orderDetails);
        return "/orderDetails/orderDetails";
    }

    @PostMapping("/delete-order-details/{id}")
    public String deleteOrderDetails(Model model,
                               @PathVariable Integer number,
                                     @PathVariable String code) {
        try {
            service.deleteById(number,code);
            return "redirect:/orderDetails/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/orderDetails/orderDetails";
        }
    }

   // @PutMapping("/update-order-details")
   // public void updateOrderDetails(@RequestBody OrderDetails orderDetails) {
   //     try {
   //         this.service.update(orderDetails);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }

   // @DeleteMapping("/delete-order-details")
   // public void deleteOrderDetails(@RequestParam Integer number,
   //                                @RequestParam String code) {
   //     try {
   //         this.service.deleteById(number, code);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
}
