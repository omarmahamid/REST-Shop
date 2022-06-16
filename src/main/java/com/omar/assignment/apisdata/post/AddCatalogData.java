package com.omar.assignment.apisdata.post;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;
import com.omar.assignment.entities.Catalog;

public class AddCatalogData implements IDataAPI {

    private long ownerId;
    private Catalog catalog;

    @Override
    public void populate(JsonObject request) {
        this.ownerId = request.get("ownerId").getAsLong();
        this.catalog = new Catalog(
                request.get("catalog").getAsJsonObject().get("id").getAsLong(),
                request.get("catalog").getAsJsonObject().get("name").getAsString()
        );
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Catalog getCatalog() {
        return catalog;
    }
}
