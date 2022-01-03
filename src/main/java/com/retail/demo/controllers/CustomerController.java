package com.retail.demo.controllers;

import com.retail.demo.models.Customer;
import com.retail.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list-all")
    public List<Customer> getCustomers() {
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/top-ten")
    public List<Customer> getTopTen() {
        return this.customerService.getTopTen();
    }

    @GetMapping("/bottom-ten")
    public List<Customer> getBottomTen() {
        return this.customerService.getBottomTen();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/name/{name}")
    public Customer getCustomerByName(@PathVariable String name) {
        return this.customerService.findByName(name);
    }

    @PostMapping("/add-customer")
    public void addCustomer(@RequestBody Customer customer) {
        try {
            this.customerService.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-customer")
    public void updateCustomer(@RequestBody Customer customer) {
        try {
            this.customerService.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        try {
            this.customerService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
