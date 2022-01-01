package com.retail.demo.services;

import com.retail.demo.models.Order;
import com.retail.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(repository.findAll());
    }
}
