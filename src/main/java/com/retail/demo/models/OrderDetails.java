package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailsId.class)
public class OrderDetails {

    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "productcode", insertable = false, updatable = false)
    private String productCode;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordernumber", insertable = false, updatable = false)
    private Integer orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordernumber")
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonBackReference(value = "order_details-order")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productcode")
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonBackReference(value = "product-order_details")
    private Product product;

    @Column(name = "quantityordered")
    private Integer quantityOrdered;

    @Column(name = "priceeach")
    private Double priceEach;

    @Column(name = "orderlinenumber")
    private Integer orderLineNumber;

    public OrderDetails() {}

    public OrderDetails(String productCode, Integer orderNumber, Integer quantityOrdered, Double priceEach, Integer orderLineNumber) {
        this.productCode = productCode;
        this.orderNumber = orderNumber;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Double priceEach) {
        this.priceEach = priceEach;
    }

    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return getOrderNumber().equals(that.getOrderNumber()) && getQuantityOrdered().equals(that.getQuantityOrdered())
                && Double.compare(that.getPriceEach(), getPriceEach()) == 0
                && getOrderLineNumber().equals(that.getOrderLineNumber())
                && getProductCode().equals(that.getProductCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode(), getOrderNumber(), getQuantityOrdered(), getPriceEach(),
                getOrderLineNumber());
    }
}
