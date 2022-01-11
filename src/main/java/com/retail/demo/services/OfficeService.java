package com.retail.demo.services;

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
        return new ArrayList<>(this.repository.findAll());
    }

    private boolean existsById(Integer id) {
        return this.repository.existsById(id);
    }

    public Office findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public Office findByCity(String city) {
        return this.repository.getOfficeByCity(city);
    }

    public List<Office> findByCountry(String country) {
        return this.repository.getAllByCountry(country);
    }

    public Office save(Office office) throws Exception {

        if (office.getOfficeCode() == null) {
            Integer id = Math.toIntExact(count() + 1);
            office.setOfficeCode(id);
        }

        if (office.getOfficeCode() != null && existsById(office.getOfficeCode())) {
            throw new Exception("Office already exists" + office.getOfficeCode());
        }
        return this.repository.save(office);
    }

    public void update(Office office) throws Exception {

        if (!existsById(office.getOfficeCode())) {
            throw new Exception("Cannot find office in: " + office.getCity());
        }

        if (office.getOfficeCode() == null) {
            throw new Exception("There was no id for office in: " + office.getCity());
        }
        this.repository.save(office);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find customer with id: " + id);
        }
        else {
            repository.deleteById(id);
        }
    }

    public Long count() {
        return repository.count();
    }
}
