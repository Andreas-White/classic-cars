package com.retail.demo.repositories;

import com.retail.demo.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
}
