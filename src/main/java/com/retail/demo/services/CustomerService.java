package com.retail.demo.services;

import com.retail.demo.models.Customer;
import com.retail.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(repository.findAll());
    }

    public List<Customer> getTopTen() {
        return new ArrayList<>(this.repository.getTopTenCustomers());
    }

    public List<Integer> getTopTenAmount() {
        return new ArrayList<>(this.repository.getTopTenCustomersAmount());
    }

    public List<Customer> getBottomTen() {
        return new ArrayList<>(this.repository.getBottomTenCustomers());
    }

    public List<Integer> getBottomTenAmount() {
        return new ArrayList<>(this.repository.getBottomTenCustomersAmount());
    }

    public Customer getCustomerByName(String name) {
        return this.repository.getCustomerByCustomerName(name);
    }

    private boolean existsById(Integer id) {
        return this.repository.existsById(id);
    }

    public Customer findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public Customer findByName(String name) {
        return this.repository.getCustomerByCustomerName(name);
    }

    public Customer save(Customer customer) throws Exception {

        if (customer.getCustomerNumber() == null) {
            Integer id = this.repository.getMaxId() + 3;
            customer.setCustomerNumber(id);
        }

        if (existsById(customer.getCustomerNumber()) ||
                this.repository.existsDistinctByCustomerName(customer.getCustomerName())) {
            throw new Exception("Customer: " + customer.getCustomerName() + " already exists");
        }
        return this.repository.save(customer);
    }

    public void update(Customer customer) throws Exception {

        if (!existsById(customer.getCustomerNumber())) {
            throw new Exception("Cannot find customer with id: " + customer.getCustomerName());
        }

        if (customer.getCustomerNumber() == null) {
            throw new Exception("There was no id for customer: " + customer.getCustomerName());
        }
        this.repository.save(customer);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find customer with id: " + id);
        } else {
            this.repository.deleteById(id);
        }
    }

    public Long count() {
        return this.repository.count();
    }
}
