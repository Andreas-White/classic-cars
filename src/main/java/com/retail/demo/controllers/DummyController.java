package com.retail.demo.controllers;

import com.retail.demo.models.*;
import com.retail.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/")
public class DummyController {

    private EmployeeService employeeService;
    private OfficeService officeService;
    private OrderDetailsService orderDetailsService;
    private OrderService orderService;
    private PaymentService paymentService;
    private ProductService productService;
    private ProductLineService productLineService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public OfficeService getOfficeService() {
        return officeService;
    }

    @Autowired
    public void setOfficeService(OfficeService officeService) {
        this.officeService = officeService;
    }

    public OrderDetailsService getOrderDetailsService() {
        return orderDetailsService;
    }

    @Autowired
    public void setOrderDetailsService(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductLineService getProductLineService() {
        return productLineService;
    }

    @Autowired
    public void setProductLineService(ProductLineService productLineService) {
        this.productLineService = productLineService;
    }

    @GetMapping("/")
    public String introduce() {
        return "<h1>This is a Spring Boot Application Test</h1>";
    }

    @GetMapping("/home")
    public String home() {
        return "<h1>This is the home page</h1>";
    }

    @GetMapping("/allEmployees")
    public List<Employee> getEmployees() {
        return getEmployeeService().getAllEmployees();
    }

    @GetMapping("/allOffices")
    public List<Office> getOffices() {
        return getOfficeService().getAllOffices();
    }

    @GetMapping("/allOrderDetails")
    public List<OrderDetails> getOrderDetails() {
        return getOrderDetailsService().getAllOrderDetails();
    }

    @GetMapping("/allOrders")
    public List<Order> getOrders() {
        return getOrderService().getAllOrders();
    }

    @GetMapping("/allPayments")
    public List<Payment> getPayments() {
        return getPaymentService().getAllPayments();
    }

    @GetMapping("/allProducts")
    public List<Product> getProducts() {
        return getProductService().getAllProducts();
    }

    @GetMapping("/allProductLines")
    public List<ProductLine> getProductLines() {
        return getProductLineService().getAllProductLines();
    }
}
