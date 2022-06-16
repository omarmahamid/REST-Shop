package com.omar.assignment.apisdata.delete;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class RemoveItemData implements IDataAPI {

    private long itemId;
    private long ownerId;

    @Override
    public void populate(JsonObject request) {
        this.itemId = request.get("itemId").getAsLong();
        this.ownerId = request.get("ownerId").getAsLong();
    }

    public long getItemId() {
        return itemId;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
