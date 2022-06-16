package com.omar.assignment.apisdata.delete;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class RemoveCatalogData implements IDataAPI {

    private long catalogId;
    private long ownerId;

    @Override
    public void populate(JsonObject request) {
        this.catalogId = request.get("catalogId").getAsLong();
        this.ownerId = request.get("ownerId").getAsLong();
    }

    public long getCatalogId() {
        return catalogId;
    }

    public long getOwnerId() {
        return ownerId;
    }

}
