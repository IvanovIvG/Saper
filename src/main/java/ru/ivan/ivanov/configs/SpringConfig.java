package ru.ivan.ivanov.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

/**
 * @author Ivan Ivanov
 **/
@Configuration
@ComponentScan(basePackages = "ru.ivan.ivanov")
public class SpringConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}

