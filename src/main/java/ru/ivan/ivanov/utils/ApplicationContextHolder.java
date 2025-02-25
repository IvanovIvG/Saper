package ru.ivan.ivanov.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ivan.ivanov.configs.SpringConfig;

/**
 * Holds static ApplicationContext class
 *
 *  @author Ivan Ivanov
 **/
public class ApplicationContextHolder {
    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public static ApplicationContext getApplicationContext(){
        return context;
    }

}
