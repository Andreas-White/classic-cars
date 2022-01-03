package com.retail.demo.services;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.models.OrderDetailsId;
import com.retail.demo.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository repository;
    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.repository = orderDetailsRepository;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return new ArrayList<>(this.repository.findAll());
    }

    public List<OrderDetails> getAllOrderDetailsByOrderNumber(Integer number) {
        return new ArrayList<>(this.repository.getAllByOrderNumber(number));
    }

    public List<OrderDetails> getAllOrderDetailsByProductCode(String code) {
        return new ArrayList<>(this.repository.getAllByProductCode(code));
    }

    public OrderDetails findById(Integer number, String code) {
        return this.repository.getAllByOrderNumberAndProductCode(number, code);
    }

    private Boolean existsById(Integer number, String code) {
        return this.repository.existsDistinctByOrderNumberAndProductCode(number, code);
    }

    public void save(OrderDetails orderDetails) throws Exception {

        if (orderDetails.getOrderNumber() == null) {
            throw new Exception("no order number was provided");
        }

        if (!getOrderService().existsById(orderDetails.getOrderNumber())) {
            throw new Exception("Not a valid order number! No order exists with that number");
        }

        if (orderDetails.getProductCode() == null) {
            throw new Exception("no product code was provided");
        }

        if (!getProductService().existsById(orderDetails.getProductCode())) {
            throw new Exception("Not a valid product code! No product exists with that code");
        }

        if (existsById(orderDetails.getOrderNumber(), orderDetails.getProductCode())) {
            throw new Exception("Order details already exist");
        }

        this.repository.insertOrderDetails(orderDetails.getOrderNumber(), orderDetails.getProductCode(),
                orderDetails.getQuantityOrdered(), orderDetails.getPriceEach(), orderDetails.getOrderLineNumber());
    }

    public void update(OrderDetails orderDetails) throws Exception {

        if (!existsById(orderDetails.getOrderNumber(), orderDetails.getProductCode())) {
            throw new Exception("Cannot find order details");
        }

        if (orderDetails.getOrderNumber() == null) {
            throw new Exception("There was not provided order number for order details");
        }

        if (orderDetails.getProductCode() == null) {
            throw new Exception("There was not provided product code for order details");
        }

        this.repository.updateOrderDetails(orderDetails.getQuantityOrdered(), orderDetails.getPriceEach(),
                orderDetails.getOrderLineNumber(),orderDetails.getOrderNumber(), orderDetails.getProductCode());
    }

    public void deleteById(Integer number, String code) throws Exception {
        if (!existsById(number, code)) {
            throw new Exception("Cannot find order details");
        } else {
            this.repository.deleteOrderDetails(number, code);
        }
    }

    public Long count() {
        return this.repository.count();
    }
}
