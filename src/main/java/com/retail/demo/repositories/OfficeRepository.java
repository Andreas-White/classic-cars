package com.retail.demo.repositories;

import com.retail.demo.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

    Office findByOfficeCode(Integer code);
    Office getOfficeByAddressLine(String address);
    Office getOfficeByCity(String city);
    List<Office> getAllByCountry(String country);

    @Query(value = "SELECT max(officCode) FROM offices;", nativeQuery = true)
    Integer getMaxId();
}
