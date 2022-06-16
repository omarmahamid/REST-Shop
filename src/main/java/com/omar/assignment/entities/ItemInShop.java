package com.omar.assignment.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ItemInShop {

    @Id
    @Column(name = "ITEM_ID")
    private long itemId;

    //FK
    @Column(name = "OWNER_ID")
    private long ownerId;

    @Column(name = "SOLD")
    private long sold;

    public ItemInShop(long itemId, long ownerId, long sold) {
        this.itemId = itemId;
        this.ownerId = ownerId;
        this.sold = sold;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }
}
