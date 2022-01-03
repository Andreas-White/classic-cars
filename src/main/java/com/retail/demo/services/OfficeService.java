package com.retail.demo.services;

import com.retail.demo.models.Customer;
import com.retail.demo.models.Office;
import com.retail.demo.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeService {

    private final OfficeRepository repository;

    @Autowired
    public OfficeService(OfficeRepository repository) {
        this.repository = repository;
    }

    public List<Office> getAllOffices() {
        return new ArrayList<>(repository.findAll());
    }

    private boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Office findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Office findByAddress(String address) {
        return repository.getOfficeByAddressLine(address);
    }

    public Office findByCity(String city) {
        return repository.getOfficeByCity(city);
    }

    public List<Office> findByCountry(String country) {
        return repository.getAllByCountry(country);
    }

    public void save(Office office) throws Exception {

        if (office.getOfficeCode() == null) {
            Integer id = repository.getMaxId() + 3;
            office.setOfficeCode(id);
        }

        if (existsById(office.getOfficeCode())) {
            throw new Exception("Office already exists");
        }
        repository.save(office);
    }
}
