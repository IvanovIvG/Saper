package ru.ivan.ivanov.gameLogic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.net.Net;

/**
 * Draws game field in console
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class FieldDrawer {
    private  final GameData gameData;

    public void showField() {
        System.out.println("mine left: "+ gameData.getNumberOfUnflaggedMines());
        for(int lineIndex = 0; lineIndex < gameData.getFieldHeight(); lineIndex++) {
            printLine(lineIndex);
        }
        System.out.println();
    }

    private void printLine(int lineIndex) {
        for(int columnIndex = 0; columnIndex< gameData.getFieldWidth(); columnIndex++) {
            Net netToPrint = getNet(lineIndex, columnIndex);
            printNet(netToPrint);
        }
        System.out.println();
    }

    private Net getNet(int lineIndex, int columnIndex) {
        int fieldWidth = gameData.getFieldWidth();
        return gameData.getGameField().get(lineIndex*fieldWidth + columnIndex);
    }

    private void printNet(Net netToPrint) {
        switch (netToPrint.getNetState()) {
            case Closed -> System.out.print(" ");
            case Flagged -> System.out.print("!");
            case Explosed -> System.out.print("#");
            case Opened -> {
                if(netIsMined(netToPrint)){
                    System.out.print("*");
                }
                else {
                    System.out.print(netToPrint.getCloseMinesNumber());
                }
            }
        }
        System.out.print("|");
    }

    private boolean netIsMined(Net net) {
        return net.isMined();
    }

}
