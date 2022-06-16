package com.omar.assignment.apisdata.get;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

import java.util.ArrayList;
import java.util.List;

public class PurchaseData implements IDataAPI {



    private String paymentMethod;
    private long customerId;
    private List<Long> itemIds;


    @Override
    public void populate(JsonObject request) {
        this.customerId = request.get("customerId").getAsLong();
        this.paymentMethod = request.get("paymentMethod").getAsString();
        this.itemIds = createItems(request);
    }

    private List<Long> createItems(JsonObject request){
        List<Long> itemsIds = new ArrayList<>();
        request.get("items").getAsJsonObject().keySet().forEach(
                key -> itemsIds.add(request.get("items").getAsJsonObject().get(key).getAsLong())
        );
        return itemsIds;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
