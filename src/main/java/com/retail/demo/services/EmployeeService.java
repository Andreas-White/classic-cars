package com.retail.demo.services;

import com.retail.demo.models.Customer;
import com.retail.demo.models.Employee;
import com.retail.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(repository.findAll());
    }

    private boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Employee findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Employee findByName(String name, String lastName) {
        return repository.getEmployeeByFirstNameAndLastName(name, lastName);
    }

    public void save(Employee employee) throws Exception {

        if (employee.getEmployeeNumber() == null) {
            Integer id = repository.getMaxId() + 3;
            employee.setEmployeeNumber(id);
        }

        if (existsById(employee.getEmployeeNumber())) {
            throw new Exception("Employee: " + employee.getFirstName() + " "
                    + employee.getLastName() + " already exists");
        }
        repository.save(employee);
    }
}
