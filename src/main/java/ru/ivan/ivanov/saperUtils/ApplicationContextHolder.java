package ru.ivan.ivanov.saperUtils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHolder {
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public ApplicationContextHolder() {
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}