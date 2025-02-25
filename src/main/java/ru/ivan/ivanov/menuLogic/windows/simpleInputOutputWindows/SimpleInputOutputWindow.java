package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ivan.ivanov.menuLogic.windows.Window;
import ru.ivan.ivanov.utils.InputScanner;

import java.util.function.Predicate;

public abstract class SimpleInputOutputWindow implements Window {
    private final String printedText;
    private final Predicate<Integer> inputCondition;
    private InputScanner scanner;

    SimpleInputOutputWindow(){
        printedText = "This is default Window";
        inputCondition = (Integer inputInt) -> inputInt > 0;
    }

    SimpleInputOutputWindow(String printedText, Predicate<Integer> inputLambdaCondition){
        this.printedText = printedText;
        this.inputCondition = inputLambdaCondition;
    }

    public Window runWindowAndGoToNext() {
        System.out.println(printedText);
        int scannedInt = scanner.scanInt(inputCondition);
        return goToNextWindow(scannedInt);
    }

    protected abstract Window goToNextWindow(int inputInt);

    @Autowired
    public void setScanner(InputScanner scanner) {
        this.scanner = scanner;
    }
}


