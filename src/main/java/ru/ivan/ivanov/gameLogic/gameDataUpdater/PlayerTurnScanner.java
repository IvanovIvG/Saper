package ru.ivan.ivanov.gameLogic.gameDataUpdater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;
import ru.ivan.ivanov.utils.InputScanner;

import java.util.List;

/**
 * Scans turn user want to make.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class PlayerTurnScanner {
    private final InputScanner inputScanner;
    private final GameData gameData;

    private TurnOption turnOption;
    private Net turnNet;

    public Turn makeTurn(){
        printInfoText();
        setTurnOption();
        int turnLine = scanTurnLine();
        int turnColumn = scanTurnColumn();
        getTurnNet(turnLine, turnColumn);
        return new Turn(turnOption, List.of(turnNet));
    }

    private void printInfoText() {
        System.out.println("Bot can not make a turn");
        System.out.println("""
						   Enter
						   0: to open net
						   1: to put flag
						   2: to take off flag""");
    }

    private void setTurnOption() {
        int turn = inputScanner.scanInt(a-> a >= 0 && a < 3);
        switch (turn) {
            case 0 -> turnOption = TurnOption.OpenNet;
            case 1 -> turnOption = TurnOption.PutFlag;
            case 2 -> turnOption = TurnOption.TakeOffFlag;
        }
    }

    private int scanTurnLine() {
        System.out.println("Enter line of your turn");
        return inputScanner.scanInt(a-> a>=0 && a < gameData.getFieldHeight());
    }

    private int scanTurnColumn() {
        System.out.println("Enter column of your turn");
        return inputScanner.scanInt(a-> a>=0 && a< gameData.getFieldWidth());
    }

    private void getTurnNet(int turnLine, int turnColumn) {
        int netIndex = turnColumn + turnLine* gameData.getFieldWidth();
        turnNet = gameData.getGameField().get(netIndex);
    }
}
