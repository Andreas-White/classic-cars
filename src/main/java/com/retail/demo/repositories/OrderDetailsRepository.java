package com.retail.demo.repositories;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.models.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
}
