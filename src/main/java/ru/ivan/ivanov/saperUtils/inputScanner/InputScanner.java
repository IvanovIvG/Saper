package ru.ivan.ivanov.saperUtils.inputScanner;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static int scanInt(InputCondition inputCondition) {
        boolean correctIntNotScanned = true;
        int inputNumber = 0 ;
        while(correctIntNotScanned) {
            try {
                inputNumber = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("You've entered inappropriate symbol");
                scanner.next();
                continue;
            }

            if(inputCondition.checkInputCondition(inputNumber)) {
                correctIntNotScanned = false;
            }
            else {
                System.out.println("You've entered inappropriate number");
            }
        }
        return inputNumber;
    }
}
