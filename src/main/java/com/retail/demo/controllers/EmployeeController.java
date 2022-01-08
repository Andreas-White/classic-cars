package com.retail.demo.controllers;

import com.retail.demo.models.Employee;
import com.retail.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list-all")
    public String getEmployees(Model model) {
        List<Employee> employees = this.employeeService.getAllEmployees();
        String title = "All Employees";

        model.addAttribute("employees", employees);
        model.addAttribute("title",title);

        return "/employee/employee-list";
    }

  //  @GetMapping("/{id}")
  //  public Employee getEmployee(@PathVariable Integer id) {
  //      return this.employeeService.getById(id);
  //  }
//
  //  @GetMapping("/name")
  //  public Employee getEmployee(@RequestParam String first,
  //                              @RequestParam String last) {
  //      return this.employeeService.getByName(first, last);
  //  }

    @GetMapping("/all-sellers")
    public String getSellers(Model model) {
        List<Employee> employees = this.employeeService.getRepEmployees();
        String title = "All sellers";

        model.addAttribute("title", title);
        model.addAttribute("employees", employees);

        return "/employee/employee-list";
    }

    @GetMapping("/all-vps")
    public String getVPs(Model model) {
        List<Employee> employees = this.employeeService.getVPs();
        String title = "All VPs";

        model.addAttribute("title", title);
        model.addAttribute("employees", employees);

        return "/employee/employee-list";
    }

    @GetMapping("/all-managers")
    public String getManagers(Model model) {
        List<Employee> employees = this.employeeService.getManagers();
        String title = "All Managers";

        model.addAttribute("title", title);
        model.addAttribute("employees", employees);

        return "/employee/employee-list";

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
