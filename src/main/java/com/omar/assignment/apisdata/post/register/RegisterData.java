package com.omar.assignment.apisdata.post.register;

import com.google.gson.JsonObject;
import com.omar.assignment.apisdata.IDataAPI;

public class RegisterData implements IDataAPI {


    private long id;
    private String name;
    private String email;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void populate(JsonObject request) {
        setId(request.get("id").getAsLong());
        setName(request.get("name").getAsString());
        setEmail(request.get("email").getAsString());
    }
}
