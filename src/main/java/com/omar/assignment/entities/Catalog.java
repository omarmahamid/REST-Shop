package com.omar.assignment.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Catalog {

    @Id
    @Column(name = "CATALOG_ID")
    private long catalogId;

    @Column(name = "CATALOG_NAME")
    private String catalogName;

    public Catalog(long catalogId, String catalogName) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }
}
