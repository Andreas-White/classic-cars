package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "productcode")
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "productline")
    @JsonBackReference
    private ProductLine productLine;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetails> orderDetails;

    @Column(name = "productname")
    private String productName;

    @Column(name = "productvendor")
    private String productVendor;

    @Column(name = "productdescription")
    private String productDescription;

    @Column(name = "quantityinstock")
    private int quantityInStock;

    @Column(name = "buyprice")
    private double buyPrice;

    public Product() {}

    public Product(String productCode, ProductLine productLine, String productName, String productVendor,
                   String productDescription, int quantityInStock, double buyPrice) {
        this.productCode = productCode;
        this.productLine = productLine;
        this.productName = productName;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getQuantityInStock() == product.getQuantityInStock() && Double.compare(product.getBuyPrice(), getBuyPrice()) == 0 && getProductCode().equals(product.getProductCode()) && getProductLine().equals(product.getProductLine()) && getProductName().equals(product.getProductName()) && getProductVendor().equals(product.getProductVendor()) && getProductDescription().equals(product.getProductDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode(), getProductLine(), getProductName(), getProductVendor(), getProductDescription(), getQuantityInStock(), getBuyPrice());
    }
}
