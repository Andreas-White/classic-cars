package com.retail.demo.services;

import com.retail.demo.models.Payment;
import com.retail.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(repository.findAll());
    }

    private boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    public Payment findById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    public void save(Payment payment) throws Exception {

        if (payment.getCheckNumber() == null) {
            String id = this.repository.getMaxId();
            String numPart = String.valueOf(Integer.parseInt(id.substring(2)) + 3);
            String newId = id.substring(0,2).concat(numPart);
            payment.setCheckNumber(newId);
        }

        if (existsById(payment.getCheckNumber())) {
            throw new Exception("Payment with id:" + payment.getCheckNumber() + " already exists");
        }
        this.repository.save(payment);
    }

    public void update(Payment payment) throws Exception {

        if (!existsById(payment.getCheckNumber())) {
            throw new Exception("Cannot find payment with id: " + payment.getCheckNumber());
        }

        if (payment.getCustomerNumber() == null) {
            throw new Exception("There was no id for payment: " + payment.getCheckNumber());
        }
        this.repository.save(payment);
    }

    public void deleteById(String id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find customer with id: " + id);
        }
        else {
            this.repository.deleteById(id);
        }
    }

    public Long count() {
        return this.repository.count();
    }
}
