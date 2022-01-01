package com.retail.demo.models;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailsId implements Serializable {

    private int orderNumber;
    private String productCode;

    public OrderDetailsId() {}

    public OrderDetailsId(int orderNumber, String productCode) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailsId)) return false;
        OrderDetailsId that = (OrderDetailsId) o;
        return orderNumber == that.orderNumber && productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }
}
