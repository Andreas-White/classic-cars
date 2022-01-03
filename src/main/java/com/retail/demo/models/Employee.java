package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employeenumber")
    private Integer employeeNumber;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officecode", insertable = false, updatable = false)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonBackReference(value = "office-employee")
    private Office office;

    @Column(name = "officecode")
    private Integer officeCode;

    @Column(name = "jobtitle")
    private String jobTitle;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonManagedReference(value = "employee-customer")
    private List<Customer> customers;

    public Employee() {}

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Integer getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(Integer officeCode) {
        this.officeCode = officeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployeeNumber().equals(employee.getEmployeeNumber()) && getLastName().equals(employee.getLastName())
                && getFirstName().equals(employee.getFirstName()) && getEmail().equals(employee.getEmail())
                && getOfficeCode().equals(employee.getOfficeCode()) && getJobTitle().equals(employee.getJobTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeNumber(), getLastName(), getFirstName(),
                getEmail(), getOfficeCode(), getJobTitle());
    }
}
