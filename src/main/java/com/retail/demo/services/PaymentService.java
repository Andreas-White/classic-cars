package com.retail.demo.services;

import com.retail.demo.models.Payment;
import com.retail.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(repository.findAll());
    }
}
