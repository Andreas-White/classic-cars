package com.retail.demo.repositories;

import com.retail.demo.models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLineRepository extends JpaRepository<ProductLine, String> {
}
