package com.retail.demo.services;

import com.retail.demo.models.Customer;
import com.retail.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new ArrayList<>(repository.getTopTenCustomers());
    }

    private boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Customer findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Customer findByName(String name) {
        return repository.getCustomerByCustomerName(name);
    }

    public void save(Customer customer) throws Exception {

        if (customer.getCustomerNumber() != null && existsById(customer.getCustomerNumber())) {
            throw new Exception("User with id: " + customer.getCustomerName() + " already exists");
        }
        repository.save(customer);
    }

    public void update(Customer customer) throws Exception {

        if (!existsById(customer.getCustomerNumber())) {
            throw new Exception("Cannot find User with id: " + customer.getCustomerName());
        }
        repository.save(customer);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find Customer with id: " + id);
        }
        else {
            repository.deleteById(id);
        }
    }

    public Long count() {
        return repository.count();
    }
}
