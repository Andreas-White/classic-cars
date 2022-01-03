package com.retail.demo.repositories;

import com.retail.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT max(orderNumber) FROM orders;", nativeQuery = true)
    Integer getMaxId();
}
