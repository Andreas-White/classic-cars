package com.retail.demo.controllers;

import com.retail.demo.models.Payment;
import com.retail.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public List<Payment> getPayments() {
        return this.service.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable String id) {
        return this.service.findById(id);
    }

    @PostMapping("/add-payment")
    public void addPayment(@RequestBody Payment payment) {
        try {
            this.service.save(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-payment")
    public void updatePayment(@RequestBody Payment payment) {
        try {
            this.service.update(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-payment/{id}")
    public void deletePayment(@PathVariable String id) {
        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
