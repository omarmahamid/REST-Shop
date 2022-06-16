package com.omar.assignment.exception;

import java.io.IOException;

public class TypeAPINotFoundException extends IOException {

    private static final long serialVersionUID = 1L;

    public TypeAPINotFoundException(String type){
        super("API not found: " + type);
    }
}
