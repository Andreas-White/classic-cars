package com.retail.demo.controllers;

import com.retail.demo.models.Employee;
import com.retail.demo.models.Product;
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
    public String getTopTen(Model model) {
        List<Employee> employees = this.employeeService.getTopTenEmployees();
        String title = "Top Ten Employees";

        model.addAttribute("employees", employees);
        model.addAttribute("title", title);
        return "/employee/employee-list";
    }

    @GetMapping("/bottom-ten")
    public String getBottomTen(Model model) {
        List<Employee> employees = this.employeeService.getBottomTenEmployees();
        String title = "Bottom Ten employees";

        model.addAttribute("employees", employees);
        model.addAttribute("title", title);
        return "/employee/employee-list";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(Model model,
                          @PathVariable Integer id) {
        Employee employee = null;
        try {
            employee  = employeeService.getById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No customer found with that ID");
        }
        model.addAttribute("employee", employee);
        return "/employee/employee";
    }

    @GetMapping("/employees-office/{city}")
    public String getEmployeesByOffice(Model model,
                                               @PathVariable String city) {



        List<Employee> employees = this.employeeService.getEmployeesByOffice(city);
        String title = "All employees in:" + city;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("employees", employees);

        return "/employee/employee-list";
    }


     @GetMapping("/add-employee")
     public String getAddEmployee(Model model) {
         Employee employee = new Employee();
         model.addAttribute("add", true);
         model.addAttribute("employee", employee);

         return "employee/update";
     }

    @PostMapping("/add-employee")
    public String processAddEmployee(Model model,
                                    @ModelAttribute("employee") Employee employee) {
        try {
            Employee newEmployee = employeeService.save(employee);
            return "redirect:/employee/" + newEmployee.getEmployeeNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/employee/update";
        }
    }

    @GetMapping("/update-employee/{id}")
    public String getUpdateEmployee(Model model, @PathVariable Integer id) {
        Employee employee = null;
        try {
            employee = employeeService.getById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("employee", employee);
        return "/employee/update";
    }

    @PostMapping("/update-employee/{id}")
    public String processUpdateEmployee(Model model,
                                       @PathVariable Integer id,
                                       @ModelAttribute("employee") Employee employee) {
        try {
            employee.setEmployeeNumber(id);
            employeeService.update(employee);
            return "redirect:/employee/" + employee.getEmployeeNumber();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/employee/update";
        }
    }

    @GetMapping("/delete-employee/{id}")
    public String getDeleteEmployee(Model model,
                                   @PathVariable Integer id) {
        Employee employee = null;
        try {
            employee = employeeService.getById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("employee", employee);
        return "/employee/employee";
    }

    @PostMapping("/delete-employee/{id}")
    public String deleteEmployee(Model model,
                                @PathVariable Integer id) {
        try {
            employeeService.deleteById(id);
            return "redirect:/employee/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/employee/employee";
        }
    }

   // @PutMapping("/update-employee")
   // public void updateCustomer(@RequestBody Employee employee) {
   //     try {
   //         this.employeeService.update(employee);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
//
   // @DeleteMapping("/delete-employee/{id}")
   // public void deleteCustomer(@PathVariable Integer id) {
   //     try {
   //         this.employeeService.deleteById(id);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
}
