package com.omar.assignment.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ServerResponse {

    private boolean ok = false;
    private String type;
    private HttpStatus status;
    private List<?> entities;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<?> getEntities() {
        return entities;
    }

    public void setEntities(List<?> entities) {
        this.entities = entities;
    }
}
