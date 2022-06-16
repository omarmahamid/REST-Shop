package com.omar.assignment.apisdata.put;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class UpdateCatalogData implements IDataAPI {

    private long ownerId;
    private long catalogId;
    private String catalogName;

    @Override
    public void populate(JsonObject request) {
        this.catalogName = request.get("updates").getAsJsonObject().get("name").getAsString();
        this.catalogId = request.get("catalogId").getAsLong();
        this.ownerId = request.get("ownerId").getAsLong();
    }


    public long getOwnerId() {
        return ownerId;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }
}
