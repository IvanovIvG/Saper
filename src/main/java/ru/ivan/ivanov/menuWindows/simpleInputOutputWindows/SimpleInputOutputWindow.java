package ru.ivan.ivanov.menuWindows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ivan.ivanov.menuWindows.Window;
import ru.ivan.ivanov.utils.InputScanner;

import java.util.function.Predicate;

/**
 * Contract for app's window
 * <p>
 * Such window prints text after scan number. Depending on number, it goes to next window.
 *
 *  @author Ivan Ivanov
 **/
public abstract class SimpleInputOutputWindow implements Window {
    private final String printedText;
    private final Predicate<Integer> inputCondition;
    private InputScanner scanner;

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


