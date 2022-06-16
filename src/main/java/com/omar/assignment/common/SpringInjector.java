package com.omar.assignment.common;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Scope("singleton")
public class SpringInjector {

    private static ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringInjector.applicationContext = applicationContext;
    }


    public static <T> T getInstanceByType(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> Collection<T> getAllInstancesOfType(Class<T> requiredType) {
        return applicationContext.getBeansOfType(requiredType).values();
    }

}
