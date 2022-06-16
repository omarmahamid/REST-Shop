package com.omar.assignment.entities;


import javax.persistence.*;

@Entity
@Table
public class Purchases {


    @Id
    @Column(name = "PURCHASE_ID")
    private long itemId;

    //FK
    @Column(name = "CUSTOMER_ID")
    private long customerId;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "TOTAL")
    private double total;

    @Column(name = "DISCOUNT")
    private double discount;

    public Purchases(long itemId, long customerId, String paymentMethod, long total, double discount) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.discount = discount;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
