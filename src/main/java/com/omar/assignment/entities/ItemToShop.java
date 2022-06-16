package com.omar.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ItemToShop {

    @Id
    @Column(name = "ITEM_ID")
    private long itemId;

    //FK
    @Column(name = "CATALOG_ID")
    private long catalogId;

    //FK
    @Column(name = "SELLER_ID")
    private long sellerId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "PRICE")
    private long price;


    public ItemToShop(long itemId, long categoryId, long sellerId,String itemName, long price) {
        this.itemId = itemId;
        this.catalogId = categoryId;
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getCategoryId() {
        return catalogId;
    }

    public void setCategoryId(long categoryId) {
        this.catalogId = categoryId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
