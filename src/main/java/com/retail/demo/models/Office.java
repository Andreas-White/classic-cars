package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "offices")
public class Office {

    @Id
    @Column(name = "officecode")
    private Integer officeCode;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "addressline")
    private String addressLine;

    @Column(name = "country")
    private String country;

    @Column(name = "postalcode")
    private String postalCode;

    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JsonManagedReference(value = "office-employee")
    private List<Employee> employees;

    public Office() {}

    public Office(Integer officeCode, String city, String phone, String addressLine, String country, String postalCode) {
        this.officeCode = officeCode;
        this.city = city;
        this.phone = phone;
        this.addressLine = addressLine;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Integer getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(Integer officeCode) {
        this.officeCode = officeCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return getOfficeCode() == office.getOfficeCode() && getCity().equals(office.getCity()) && getPhone().equals(office.getPhone()) && getAddressLine().equals(office.getAddressLine()) && getCountry().equals(office.getCountry()) && getPostalCode().equals(office.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOfficeCode(), getCity(), getPhone(), getAddressLine(), getCountry(), getPostalCode());
    }
}
