package com.omar.assignment.apisdata.put;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class UpdateItemData implements IDataAPI {

    private long ownerId;
    private long itemId;
    private String itemName;
    private Long price;
    private Long catalogId;


    @Override
    public void populate(JsonObject request) {

        this.ownerId = request.get("ownerId").getAsLong();
        this.itemId = request.get("itemId").getAsLong();
        populateNonNecessary(request);

    }

    private void populateNonNecessary(JsonObject request){
        JsonElement name = request.get("updates").getAsJsonObject().get("name");
        JsonElement price = request.get("updates").getAsJsonObject().get("price");
        JsonElement catalog = request.get("updates").getAsJsonObject().get("catalogId");

        this.itemName = name != null ? name.getAsString() : null;
        this.price = price != null ? price.getAsLong() : null;
        this.catalogId = catalog != null ? catalog.getAsLong() : null;

    }


    public long getOwnerId() {
        return ownerId;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getPrice() {
        return price;
    }

    public Long getCatalogId() {
        return catalogId;
    }
}
