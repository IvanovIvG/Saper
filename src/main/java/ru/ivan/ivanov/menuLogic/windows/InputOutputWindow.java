package ru.ivan.ivanov.menuLogic.windows;

import ru.ivan.ivanov.saperUtils.inputScanner.InputScanner;
import ru.ivan.ivanov.saperUtils.inputScanner.InputCondition;

public abstract class InputOutputWindow implements Window{
    private final String printedText;
    protected final InputCondition inputCondition;
    protected int scannedInt;

    InputOutputWindow(){
        printedText = "This is default Window";
        inputCondition = (int inputInt) -> inputInt > 0;
    }

    InputOutputWindow(String printedText, InputCondition inputLambdaCondition){
        this.printedText = printedText;
        this.inputCondition = inputLambdaCondition;
    }

    public Window runWindowAndGoToNext() {
        printText(printedText);
        scannedInt = InputScanner.scanInt(inputCondition);
        return goToNextWindow(scannedInt);
    }

    protected void printText(String Text) {
        System.out.println(Text);
    }

    protected abstract Window goToNextWindow(int inputInt);
}


