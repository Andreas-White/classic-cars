package com.retail.demo.controllers;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.models.OrderDetailsDT;
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
        return "/orderDetails/orderDetails-list";
    }

    @GetMapping("/list-order-number/{number}")
    public String getOrderDetailsFromOrderNumber(Model model,
                                                 @PathVariable Integer number) {

        List<OrderDetails> orderDetails = this.service.getAllOrderDetailsByOrderNumber(number);
        String title = "All OrderDetails from order: " + number;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("orderDetails", orderDetails);

        return "/orderDetails/orderDetails-list";
    }

    @GetMapping("/list-product-code/{code}")
    public String getOrderDetailsFromProductCode(Model model,
                                                 @PathVariable String code) {

        List<OrderDetails> orderDetails = this.service.getAllOrderDetailsByProductCode(code);
        String title = "All OrderDetails containing product with code: " + code;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("orderDetails", orderDetails);

        return "/orderDetails/orderDetails-list";
    }

    @GetMapping("/{number}/{code}")
    public String getOrderDetailsById(Model model, @PathVariable Integer number, @PathVariable String code) {
        OrderDetails orderDetails = null;
        try {
            orderDetails = service.findById(number, code);                          //////////!!!
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
    public String addOrderDetails(Model model,
                                  @ModelAttribute("office") OrderDetailsDT orderDetailsDT) {

        OrderDetails newOrderDetails = new OrderDetails();
        try {

            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setOrderNumber(service.convert(orderDetailsDT.getOrderNumber()));
            orderDetails.setProductCode(orderDetailsDT.getProductCode());
            orderDetails.setQuantityOrdered(orderDetailsDT.getQuantityOrdered());
            orderDetails.setPriceEach(orderDetailsDT.getPriceEach());
            orderDetails.setOrderLineNumber(orderDetailsDT.getOrderLineNumber());

            newOrderDetails = service.save(orderDetails);
            return "redirect:/order-details/" + newOrderDetails.getOrderNumber() + "/" + newOrderDetails.getProductCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "redirect:/order-details/" + service.convert(orderDetailsDT.getOrderNumber())
                    + "/" + orderDetailsDT.getProductCode();
        }
    }

    @GetMapping("/update-order-details/{number}/{code}")
    public String getUpdateOrderDetails(Model model,
                                        @PathVariable Integer number,
                                        @PathVariable String code) {
        OrderDetails orderDetails = null;
        try {
            orderDetails = service.findById(number, code);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("orderDetails", orderDetails);
        return "/orderDetails/update";
    }

    @PostMapping("/update-order-details/{number}/{code}")
    public String processUpdateOrderDetails(Model model,
                                            @PathVariable Integer number,
                                            @PathVariable String code,
                                            @ModelAttribute("orderDetails") OrderDetailsDT orderDetailsDT) {
        try {

            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setOrderNumber(number);
            orderDetails.setProductCode(code);
            orderDetails.setQuantityOrdered(orderDetailsDT.getQuantityOrdered());
            orderDetails.setPriceEach(orderDetailsDT.getPriceEach());
            orderDetails.setOrderLineNumber(orderDetailsDT.getOrderLineNumber());

            service.update(orderDetails);
            return "redirect:/order-details/" + orderDetails.getOrderNumber() + "/" + orderDetails.getProductCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/orderDetails/update";
        }
    }

    @GetMapping("/delete-order-details/{number}/{code}")
    public String getDeleteOrderDetails(Model model,
                                        @PathVariable Integer number,
                                        @PathVariable String code) {

        OrderDetails orderDetails = null;
        try {
            orderDetails = service.findById(number, code);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("orderDetails", orderDetails);
        return "/orderDetails/orderDetails";
    }

    @PostMapping("/delete-order-details/{number}/{code}")
    public String deleteOrderDetails(Model model,
                                     @PathVariable Integer number,
                                     @PathVariable String code) {
        try {
            service.deleteById(number, code);
            return "redirect:/order-details/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/orderDetails/orderDetails";
        }
    }
}
