package com.retail.demo.repositories;

import com.retail.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select cus.* " +
            "from customers cus " +
            "join payments on cus.customerNumber=payments.customerNumber " +
            "group by cus.customerNumber " +
            "order by sum(payments.amount) desc limit 10;",nativeQuery = true)
    List<Customer> getTopTenCustomers();
}
