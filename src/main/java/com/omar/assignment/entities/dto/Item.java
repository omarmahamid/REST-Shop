package com.omar.assignment.entities.dto;

public class Item {

    private long id;
    private String name;
    private long price;
    private long sellerId;
    private long catalogId;

    public Item(long id, String name, long price, long sellerId, long catalogId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.catalogId = catalogId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
}
