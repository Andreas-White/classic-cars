package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customernumber")
    private Integer customerNumber;

    @Column(name = "customername")
    private String customerName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "addressline1")
    private String 	addressLine1;

    @Column(name = "city")
    private String 	city;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "salesperemployeenumber")
    @JsonBackReference(value = "employee-customer")
    private Employee employee;

    @Column(name = "creditlimit")
    private double creditLimit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference(value = "payment-customer")
    private List<Payment> payments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference(value = "order-customer")
    private List<Order> orders;

    public Customer() {}

    public Customer(String customerName, String phone,
                    String addressLine1, String city, String country) {
        //this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getCustomerNumber() == customer.getCustomerNumber()
                && getCustomerName().equals(customer.getCustomerName()) && getPhone().equals(customer.getPhone())
                && getAddressLine1().equals(customer.getAddressLine1()) && getCity().equals(customer.getCity())
                && getCountry().equals(customer.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerNumber(), getCustomerName(), getPhone(), getAddressLine1(), getCity(), getCountry());
    }
}
