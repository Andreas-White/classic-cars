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
        return new ArrayList<>(repository.findAll());
    }
}
