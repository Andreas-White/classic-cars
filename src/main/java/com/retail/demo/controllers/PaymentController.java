package com.retail.demo.controllers;

import com.retail.demo.models.Payment;
import com.retail.demo.models.PaymentDT;
import com.retail.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public String getPayments(Model model) {
        List<Payment> payments = this.service.getAllPayments();
        String title = "All payments";

        model.addAttribute("payments", payments);
        model.addAttribute("title", title);
        return "/payment/payment-list";
    }

    @GetMapping("/{id}")
    public String getPaymentById(Model model,
                                 @PathVariable String id) {
        Payment payment = null;
        try {
            payment = service.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No payment found with that ID");
        }
        model.addAttribute("payment", payment);
        return "/payment/payment";
    }

    @GetMapping("/add-payment")
    public String getAddPayment(Model model) {

        Payment payment = new Payment();

        model.addAttribute("add", true);
        model.addAttribute("payment", payment);

        return "payment/update";
    }

    @PostMapping("/add-payment")
    public String addPayment(Model model,
                             @ModelAttribute("payment") PaymentDT paymentDT) {
        try {
            Payment newPayment1 = new Payment();
            newPayment1.setCheckNumber(paymentDT.getCheckNumber());
            newPayment1.setPaymentDate(service.convert(paymentDT.getPaymentDate()));
            newPayment1.setAmount(paymentDT.getAmount());
            newPayment1.setCustomerNumber(paymentDT.getCustomerNumber());

            Payment newPayment = service.save(newPayment1);
            return "redirect:/payment/" + newPayment.getCheckNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/payment/update";
        }
    }

    @GetMapping("/update-payment/{id}")
    public String getUpdatePayment(Model model,
                                   @PathVariable String id) {
        Payment payment = null;
        try {
            payment = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("payment", payment);
        return "/payment/update";
    }

    @PostMapping("/update-payment/{id}")
    public String processUpdatePayment(Model model,
                                       @PathVariable String id,
                                       @ModelAttribute("payment") PaymentDT paymentDT) {
        try {
            Payment newPayment1 = new Payment();
            newPayment1.setCheckNumber(id);
            newPayment1.setPaymentDate(service.convert(paymentDT.getPaymentDate()));
            newPayment1.setAmount(paymentDT.getAmount());
            newPayment1.setCustomerNumber(paymentDT.getCustomerNumber());


            service.update(newPayment1);
            return "redirect:/payment/" + newPayment1.getCheckNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/payment/update";
        }
    }

    @GetMapping("/delete-payment/{id}")
    public String getDeletePayment(Model model,
                                   @PathVariable String id) {
        Payment payment = null;
        try {
            payment = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("payment", payment);
        return "/payment/payment";
    }

    @PostMapping("/delete-payment/{id}")
    public String deletePayment(Model model,
                                @PathVariable String id) {
        try {
            service.deleteById(id);
            return "redirect:/payment/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/payment/payment";
        }
    }

}
