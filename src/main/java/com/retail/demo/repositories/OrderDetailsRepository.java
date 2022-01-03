package com.retail.demo.repositories;

import com.retail.demo.models.OrderDetails;
import com.retail.demo.models.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {


    List<OrderDetails> getAllByOrderNumber(Integer number);
    List<OrderDetails> getAllByProductCode(String code);
    OrderDetails getAllByOrderNumberAndProductCode(Integer number, String code);

    Boolean existsDistinctByOrderNumberAndProductCode(Integer number, String code);

    void deleteDistinctByOrderNumberAndProductCode(Integer number, String code);

    @Modifying
    @Transactional
    @Query(value = "insert into orderdetails " +
            "(`orderNumber`, `productCode`, `quantityOrdered`, `priceEach`, `orderLineNumber`) " +
            "VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    void insertOrderDetails(Integer orderNumber, String productCode, Integer quantityOrdered,
                            Double priceEach, Integer orderLineNumber);

    @Modifying
    @Transactional
    @Query(value = "update orderdetails " +
            "set `quantityOrdered`=?1,`priceEach`=?2,`orderLineNumber`=?3 " +
            "where `orderNumber`=?4 and `productCode`=?5", nativeQuery = true)
    void updateOrderDetails(Integer quantityOrdered, Double priceEach,
                            Integer orderLineNumber, Integer orderNumber, String productCode);

    @Modifying
    @Transactional
    @Query(value = "delete from orderdetails where orderNumber=?1 and productCode=?2", nativeQuery = true)
    void deleteOrderDetails( Integer orderNumber, String productCode);
}
