package ru.ivan.ivanov.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class InputScanner {
    private final Scanner scanner;

    public int scanInt(Predicate<Integer> inputCondition) {
        int inputNumber = 0 ;
        while(true) {
            try {
                inputNumber = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("You've entered inappropriate symbol");
                scanner.next();
                continue;
            }

            if(inputCondition.test(inputNumber)) {
                break;
            }
            else {
                System.out.println("You've entered inappropriate number");
            }
        }
        return inputNumber;
    }
}
