package com.retail.demo.repositories;

import com.retail.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT max(employeeNumber) FROM employees;", nativeQuery = true)
    Integer getMaxId();
}
