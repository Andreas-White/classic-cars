package com.retail.demo.controllers;

import com.retail.demo.models.Employee;
import com.retail.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list-all")
    public List<Employee> getEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return this.employeeService.getById(id);
    }

    @GetMapping("/name")
    public Employee getEmployee(@RequestParam String first,
                                @RequestParam String last) {
        return this.employeeService.getByName(first, last);
    }

    @GetMapping("/all-sellers")
    public List<Employee> getSellers() {
        return this.employeeService.getRepEmployees();
    }

    @GetMapping("/all-vps")
    public List<Employee> getVPs() {
        return this.employeeService.getVPs();
    }

    @GetMapping("/all-managers")
    public List<Employee> getManager() {
        return this.employeeService.getManagers();
    }

    @GetMapping("/top-ten")
    public List<Employee> getTopTen() {
        return this.employeeService.getTopTenEmployees();
    }

    @GetMapping("/bottom-ten")
    public List<Employee> getBottomTen() {
        return this.employeeService.getBottomTenEmployees();
    }

    @GetMapping("/employees-office/{city}")
    public List<Employee> getEmployeesByOffice(@PathVariable String city) {
        return this.employeeService.getEmployeesByOffice(city);
    }


    @PostMapping("/add-employee")
    public void addCustomer(@RequestBody Employee employee) {
        try {
            this.employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update-employee")
    public void updateCustomer(@RequestBody Employee employee) {
        try {
            this.employeeService.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        try {
            this.employeeService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
