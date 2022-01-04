package com.retail.demo.repositories;

import com.retail.demo.models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

    @Query(value = "SELECT max(productLine) FROM productlines;", nativeQuery = true)
    String getMaxId();
}
