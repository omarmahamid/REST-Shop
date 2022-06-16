package com.omar.assignment.handlers;

import com.omar.assignment.dal.JDBCTemplateExecutor;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static boolean valid(long id, JDBCTemplateExecutor jdbcTemplateExecutor, String personType){
        List<?> entities;
        switch (personType){
            case "customer":
                entities = jdbcTemplateExecutor.getCustomerById(id);
                break;
            case "owner":
                entities = jdbcTemplateExecutor.getOwnerById(id);
                break;
            default:
                entities = new ArrayList<>();
        }

        return entities != null && !entities.isEmpty();
    }
}
