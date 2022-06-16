package com.omar.assignment.entities.dto;

public class Catalog {

    private long catalogId;
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
