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
        return new ArrayList<>(this.repository.findAll());
    }

    private boolean existsById(Integer id) {
        return this.repository.existsById(id);
    }

    public Employee getById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public Employee getByName(String name, String lastName) {
        return this.repository.getEmployeeByFirstNameAndLastName(name, lastName);
    }

    public List<Employee> getEmployeesByOffice(String city) {
        return this.repository.getEmployeesByOffice_City(city);
    }

    public List<Employee> getTopTenEmployees() {
        return this.repository.getTopTenEmployees();
    }

    public List<Integer> getTopTenIncomes() {
        return this.repository.getTopTenEmployeesIncome();
    }

    public List<Employee> getBottomTenEmployees() {
        return this.repository.getBottomTenEmployees();
    }

    public List<Integer> getBottomTenIncomes() {
        return this.repository.getBottomTenEmployeesIncome();
    }

    public List<Employee> getRepEmployees() {
        return this.repository.getAllSellers();
    }

    public List<Employee> getVPs() {
        return this.repository.getAllVPs();
    }

    public List<Employee> getManagers() {
        return this.repository.getAllManagers();
    }

    public void save(Employee employee) throws Exception {

        if (employee.getEmployeeNumber() == null) {
            Integer id = this.repository.getMaxId() + 3;
            employee.setEmployeeNumber(id);
        }

        if (existsById(employee.getEmployeeNumber())) {
            throw new Exception("Employee: " + employee.getFirstName() + " "
                    + employee.getLastName() + " already exists");
        }
        this.repository.save(employee);
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
        this.repository.save(employee);
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find employee with id: " + id);
        }
        else {
            this.repository.deleteById(id);
        }
    }

    public Long count() {
        return repository.count();
    }
}
