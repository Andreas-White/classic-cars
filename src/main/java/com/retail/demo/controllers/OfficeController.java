package com.retail.demo.controllers;

import com.retail.demo.models.Office;
import com.retail.demo.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/list-all")
    public List<Office> getAllOffices() {
        return new ArrayList<>(this.officeService.getAllOffices());
    }

    @GetMapping("/{id}")
    public Office getOfficeById(@PathVariable Integer id) {
        return this.officeService.findById(id);
    }

    @GetMapping("/city")
    public Office getAllOffices(@RequestParam String city) {
        return this.officeService.findByCity(city);
    }

    @GetMapping("/list-all/{country}")
    public List<Office> getAllOfficesByCountry(@PathVariable String country) {
        return new ArrayList<>(this.officeService.findByCountry(country));
    }

    @PostMapping("/add-office")
    public void addOffice(@RequestBody Office office) {
        try {
            this.officeService.save(office);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-office")
    public void updateOffice(@RequestBody Office office) {
        try {
            this.officeService.update(office);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-office/{id}")
    public void deleteOffice(@PathVariable Integer id) {
        try {
            this.officeService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
