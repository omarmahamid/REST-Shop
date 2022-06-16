package com.omar.assignment.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.omar.assignment.apisdata.factory.DataAPIFactory;
import com.omar.assignment.apisdata.IDataAPI;

public class DataHolder {

    private String body;
    private String type;
    private IDataAPI dataAPI;


    public DataHolder(String body){
        this.body = body;
    }

    public void parse(){

        JsonObject request = JsonParser.parseString(body).getAsJsonObject();

        String type = request.get("type").getAsString();

        IDataAPI dataAPI = DataAPIFactory.getInstance(type);

        dataAPI.populate(request);

        this.type = type;
        this.dataAPI = dataAPI;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public IDataAPI getDataAPI(){
        return dataAPI;
    }
}
