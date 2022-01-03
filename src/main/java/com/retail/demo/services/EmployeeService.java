package com.retail.demo.services;

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

    public Employee getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Employee getByName(String name, String lastName) {
        return repository.getEmployeeByFirstNameAndLastName(name, lastName);
    }

    public List<Employee> getEmployeesByOffice(String city) {
        return repository.getEmployeesByOffice_City(city);
    }

    public List<Employee> getTopTenEmployees() {
        return repository.getTopTenEmployees();
    }

    public List<Integer> getTopTenIncomes() {
        return repository.getTopTenEmployeesIncome();
    }

    public List<Employee> getBottomTenEmployees() {
        return repository.getBottomTenEmployees();
    }

    public List<Integer> getBottomTenIncomes() {
        return repository.getBottomTenEmployeesIncome();
    }

    public List<Employee> getRepEmployees() {
        return repository.getAllSellers();
    }

    public List<Employee> getVPs() {
        return repository.getAllVPs();
    }

    public List<Employee> getManagers() {
        return repository.getAllManagers();
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

    public void update(Employee employee) throws Exception {

        if (!existsById(employee.getEmployeeNumber())) {
            throw new Exception("Cannot find employee with id: " + employee.getFirstName() + " "
                    + employee.getLastName());
        }

        if (employee.getEmployeeNumber() == null) {
            throw new Exception("There was no id for employee: " + employee.getFirstName() + " "
                    + employee.getLastName());
        }
        repository.save(employee);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find employee with id: " + id);
        }
        else {
            repository.deleteById(id);
        }
    }

    public Long count() {
        return repository.count();
    }
}
