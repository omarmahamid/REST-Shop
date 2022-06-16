package com.omar.assignment.apisdata.post;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class AddItemIntoShopData implements IDataAPI {

    private long itemId;
    private long ownerId;

    @Override
    public void populate(JsonObject request) {
        this.itemId = request.get("item").getAsJsonObject().get("id").getAsLong();
        this.ownerId = request.get("item").getAsJsonObject().get("ownerId").getAsLong();
    }

    public long getItemId() {
        return itemId;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
