package com.retail.demo.controllers;

import com.retail.demo.models.Customer;
import com.retail.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list-all")
    public String getCustomers(Model model) {
        List<Customer> customers = this.customerService.getAllCustomers();
        String title = "All Customers";

        model.addAttribute("customers", customers);
        model.addAttribute("title", title);
        return "/customer/customer-list";
        //return this.customerService.getAllCustomers();
    }

    @GetMapping("/top-ten")
    public String getTopTen(Model model) {
        List<Customer> customers = this.customerService.getTopTen();
        String title = "Top 10 Customers";

        model.addAttribute("customers", customers);
        model.addAttribute("title", title);
        return "/customer/customer-list";
    }

    @GetMapping("/bottom-ten")
    public String getBottomTen(Model model) {
        List<Customer> customers = this.customerService.getBottomTen();
        String title = "Bottom 10 Customers";

        model.addAttribute("customers", customers);
        model.addAttribute("title", title);
        return "/customer/customer-list";
    }

    @GetMapping("/{id}")
    public String getCustomerById(Model model,
                                  @PathVariable Integer id) {
        Customer customer = null;
        try {
            customer = customerService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No customer found with that ID");
        }
        model.addAttribute("customer", customer);
        return "/customer/customer";
    }

    @GetMapping("/name/{name}")
    public Customer getCustomerByName(@PathVariable String name) {
        return this.customerService.findByName(name);
    }

    @GetMapping("/add-customer")
    public String getAddCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("add", true);
        model.addAttribute("customer", customer);

        return "customer/update";
    }

    @PostMapping("/add-customer")
    public String processAddCustomer(Model model,
                                   @ModelAttribute("customer") Customer customer) {
        try {
            Customer newCustomer = customerService.save(customer);
            return "redirect:/customer/" + newCustomer.getCustomerNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/customer/update";
        }
    }

    @GetMapping("/update-customer/{id}")
    public String getUpdateCustomer(Model model, @PathVariable Integer id) {
        Customer customer = null;
        try {
            customer = customerService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("customer", customer);
        return "/customer/update";
    }

    @PostMapping("/update-customer/{id}")
    public String processUpdateCustomer(Model model,
                                 @PathVariable Integer id,
                                 @ModelAttribute("customer") Customer customer) {
        try {
            customer.setCustomerNumber(id);
            customerService.update(customer);
            return "redirect:/customer/" + customer.getCustomerNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/customer/update";
        }
    }

    @GetMapping("/delete-customer/{id}")
    public String getDeleteCustomer(Model model,
                               @PathVariable Integer id) {
        Customer customer = null;
        try {
            customer = customerService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("customer", customer);
        return "/customer/customer";
    }

    @PostMapping("/delete-customer/{id}")
    public String deleteCustomer(Model model,
                                 @PathVariable Integer id) {
        try {
            customerService.deleteById(id);
            return "redirect:/customer/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/customer/customer";
        }

    }
}
