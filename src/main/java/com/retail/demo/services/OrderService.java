package com.retail.demo.services;

import com.retail.demo.models.Customer;
import com.retail.demo.models.Order;
import com.retail.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(repository.findAll());
    }

    public Boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Order findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Order order) throws Exception {

        if (order.getOrderNumber() == null) {
            Integer id = repository.getMaxId() + 3;
            order.setOrderNumber(id);
        }

        if (existsById(order.getOrderNumber())) {
            throw new Exception("Order already exists");
        }
        repository.save(order);
    }

    public void update(Order order) throws Exception {

        if (!existsById(order.getOrderNumber())) {
            throw new Exception("Cannot find order with id: " + order.getOrderNumber());
        }

        if (order.getOrderNumber() == null) {
            throw new Exception("There was no id for order: " + order.getOrderNumber());
        }
        this.repository.save(order);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find order with id: " + id);
        }
        else {
            this.repository.deleteById(id);
        }
    }

    public Long count() {
        return this.repository.count();
    }
}
