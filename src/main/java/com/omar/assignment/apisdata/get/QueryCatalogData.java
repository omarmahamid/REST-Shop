package com.omar.assignment.apisdata.get;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class QueryCatalogData implements IDataAPI {

    private long customerId;
    private long catalogId;

    @Override
    public void populate(JsonObject request) {
        this.customerId = request.get("customerId").getAsLong();
        this.catalogId = request.get("catalogId").getAsLong();
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getCatalogId() {
        return catalogId;
    }
}
