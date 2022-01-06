package com.retail.demo.repositories;

import com.retail.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT max(productCode) FROM products;", nativeQuery = true)
    String getMaxId();

    @Query(value = "select pr.* " +
            "from products pr " +
            "join orderdetails od on pr.productCode=od.productCode " +
            "group by pr.productCode " +
            "order by count(od.productCode) desc limit 10;",nativeQuery = true)
    List<Product> getTopTenProducts();

    @Query(value = "select pr.* " +
            "from products pr " +
            "left join orderdetails od on pr.productCode=od.productCode " +
            "group by pr.productCode " +
            "order by count(od.productCode) asc limit 10;",nativeQuery = true)
    List<Product> getBottomTenProducts();
}
