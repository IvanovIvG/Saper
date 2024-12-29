package ru.ivan.ivanov.gameLogic.gameTry.fieldDrawer;

import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.TryConfig;
import ru.ivan.ivanov.gameLogic.net.Net;

import java.util.List;

public class FieldDrawer {
    private static int fieldWidth;
    private static int fieldHeight;
    private static int numberOfUnflaggedMines;
    private static List<Net> gameField;
    private static NetDrawer netDrawer;

    //this function prints all nets according to its state
    public static void showClosedField(TryConfig tryConfig) {
        netDrawer = ClosedNetDrawer.getClosedNetDrawer();
        showField(tryConfig);
    }

    //this function prints closed nets as opened
    public static void showOpenedField(TryConfig tryConfig) {
        netDrawer = OpenedNetDrawer.getOpenedNetDrawer();
        showField(tryConfig);
    }

    private static void showField(TryConfig tryConfig){
        fieldWidth = tryConfig.fieldWidth;
        fieldHeight = tryConfig.fieldHeight;
        numberOfUnflaggedMines = tryConfig.numberOfUnflaggedMines;
        gameField = tryConfig.gameField;

        System.out.println("mine left: "+ numberOfUnflaggedMines);
        for(int lineIndex = 0; lineIndex < fieldHeight; lineIndex++) {
            printLine(lineIndex);
        }
        System.out.println();
    }

    private static void printLine(int lineIndex) {
        for(int columnIndex = 0; columnIndex< fieldWidth; columnIndex++) {
            Net netToPrint = getNet(lineIndex, columnIndex);
            netDrawer.printNet(netToPrint);
        }
        System.out.println();
    }

    private static Net getNet(int lineIndex, int columnIndex) {
        return gameField.get(lineIndex*fieldWidth + columnIndex);
    }


}
