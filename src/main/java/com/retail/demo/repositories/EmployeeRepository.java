package com.retail.demo.repositories;

import com.retail.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select emp.* " +
            "from employees emp " +
            "join customers cus on cus.salesPerEmployeeNumber=emp.employeeNumber " +
            "join payments pays on cus.customerNumber=pays.customerNumber "+
            "where emp.jobTitle='Sales Rep' " +
            "group by emp.employeeNumber " +
            "order by sum(pays.amount)desc limit 10;",nativeQuery = true)
    List<Employee> getTopTenEmployees();

    @Query(value = "select emp.* " +
            "from employees emp " +
            "left outer join customers cus on cus.salesPerEmployeeNumber=emp.employeeNumber " +
            "left join payments pays on cus.customerNumber=pays.customerNumber " +
            "where emp.jobTitle='Sales Rep' " +
            "group by emp.employeeNumber " +
            "order by sum(pays.amount)asc limit 10;",nativeQuery = true)
    List<Employee> getBottomTenEmployees();

    @Query(value = "select sum(pays.amount) " +
            "from employees emp " +
            "join customers cus on cus.salesPerEmployeeNumber=emp.employeeNumber " +
            "join payments pays on cus.customerNumber=pays.customerNumber "+
            "where emp.jobTitle='Sales Rep' " +
            "group by emp.employeeNumber " +
            "order by sum(pays.amount) desc limit 10;",nativeQuery = true)
    List<Integer> getTopTenEmployeesIncome();

    @Query(value = "select sum(pays.amount) " +
            "from employees emp " +
            "left outer join customers cus on cus.salesPerEmployeeNumber=emp.employeeNumber " +
            "left join payments pays on cus.customerNumber=pays.customerNumber " +
            "where emp.jobTitle='Sales Rep' " +
            "group by emp.employeeNumber " +
            "order by sum(pays.amount) asc limit 10;",nativeQuery = true)
    List<Integer> getBottomTenEmployeesIncome();

    @Query(value = "select * from employees where jobTitle='Sales Rep';", nativeQuery = true)
    List<Employee> getAllSellers();

    @Query(value = "select * from employees where jobTitle like '%VP%';", nativeQuery = true)
    List<Employee> getAllVPs();

    @Query(value = "select * from employees where jobTitle like '%Manager%';", nativeQuery = true)
    List<Employee> getAllManagers();

    @Query
    List<Employee> getEmployeesByOffice_City(String city);

    Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT max(employeeNumber) FROM employees;", nativeQuery = true)
    Integer getMaxId();
}
