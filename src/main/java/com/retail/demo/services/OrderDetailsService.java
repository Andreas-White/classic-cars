package com.retail.demo.services;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {

    private OrderDetailsRepository repository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.repository = orderDetailsRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return new ArrayList<>(repository.findAll());
    }
}
