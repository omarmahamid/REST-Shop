package com.omar.assignment.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SELLERS")
public class Sellers {

    public Sellers(long id,
                   String name,
                   String email){
        this.sellerEmail = email;
        this.sellerName = name;
        this.sellerId = id;
    }

    @Id
    @Column(name = "SELLER_ID")
    private long sellerId;

    @Column(name = "SELLER_NAME")
    private String sellerName;

    @Column(name = "SELLER_EMAIL")
    private String sellerEmail;


    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
}
