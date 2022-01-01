package com.retail.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "productlines")
public class ProductLine {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "productline")
    private String productLine;

    @OneToMany(mappedBy = "productLine",fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JsonManagedReference
    private List<Product> products;

    @Column(name = "textdescription")
    private String textDescription;

    public ProductLine() {}

    public ProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductLine)) return false;
        ProductLine that = (ProductLine) o;
        return getProductLine().equals(that.getProductLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductLine());
    }
}
