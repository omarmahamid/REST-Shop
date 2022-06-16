package com.omar.assignment.apisdata.post;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;
import com.omar.assignment.entities.dto.Item;

public class AddItemData implements IDataAPI {

    private Item item;

    @Override
    public void populate(JsonObject request) {
        this.item = new Item(
                request.get("item").getAsJsonObject().get("id").getAsLong(),
                request.get("item").getAsJsonObject().get("name").getAsString(),
                request.get("item").getAsJsonObject().get("price").getAsLong(),
                request.get("item").getAsJsonObject().get("sellerId").getAsLong(),
                request.get("item").getAsJsonObject().get("catalogId").getAsLong()
        );
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
