package com.retail.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "checknumber")
    private String checkNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "customernumber", insertable = false, updatable = false)
    @JsonBackReference(value = "payment-customer")
    private Customer customer;

    @Column(name = "customernumber")
    private Integer customerNumber;

    @Column(name = "paymentdate", columnDefinition = "DATE")
    private LocalDate 	paymentDate;

    @Column(name = "amount")
    private Double amount;

    public Payment() {}

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.getAmount(), getAmount()) == 0
                && getCheckNumber().equals(payment.getCheckNumber())
                && getCustomer().equals(payment.getCustomer())
                && getPaymentDate().equals(payment.getPaymentDate())
                && getCustomerNumber().equals(payment.getCustomerNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCheckNumber(), getCustomerNumber(),
                getCustomer(), getPaymentDate(), getAmount());
    }
}
