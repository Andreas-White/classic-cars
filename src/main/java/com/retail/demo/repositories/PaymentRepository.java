package com.retail.demo.repositories;

import com.retail.demo.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query(value = "SELECT max(checkNumber) FROM payments;", nativeQuery = true)
    String getMaxId();
}
