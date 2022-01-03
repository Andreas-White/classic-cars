package com.retail.demo.repositories;

import com.retail.demo.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

    Office getOfficeByCity(String city);
    List<Office> getAllByCountry(String country);

    @Query(value = "SELECT max(officeCode) FROM offices;", nativeQuery = true)
    Integer getMaxId();

}
