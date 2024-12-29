package ru.ivan.ivanov.gameLogic.gameTry;

import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.TryConfig;
import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;
import ru.ivan.ivanov.saperUtils.inputScanner.InputScanner;
import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.GameState;

public class TryUpdater {
    private static Turn madeTurn;
    private static TryConfig tryConfigToUpdate;

    public static void updateTryConfig(Turn turn, TryConfig tryConfig) {
        madeTurn = turn;
        tryConfigToUpdate = tryConfig;

        switch (madeTurn.turnOption) {
            case OpenNet -> updateTryIfNetOpen();
            case PutFlag -> updateTryIfPutFlag();
            case TakeOffFlag -> updateTryIfTakeOffFlag();
            case PlayerTurn -> updateTryIfPlayerTurn();
        }
    }

    private static void updateTryIfNetOpen() {
        for(Net netToOpen: madeTurn.netsToTurn) {
            if(netIsMined(netToOpen)) {
                openMinedNet(netToOpen);
            }
            else {
                openNotMinedNet(netToOpen);
            }
        }
    }

    private static boolean netIsMined(Net netToOpen) {
        return netToOpen.isMined();
    }

    private static void openMinedNet(Net netToOpen) {
        netToOpen.explose();
        tryConfigToUpdate.gameState = GameState.GameLost;
        for(Net net: tryConfigToUpdate.gameField) {
            if(net.isClosed()) net.open();
        }
    }

    private static void openNotMinedNet(Net netToOpen) {
        netToOpen.openNet(tryConfigToUpdate.gameField);
        if(gameWon()) {
            tryConfigToUpdate.gameState = GameState.GameWin;
            unflagAllNets();
        }
    }

    private static boolean gameWon() {
        return AllPossibleNetsOpened();
    }

    private static boolean AllPossibleNetsOpened() {
        for (Net net : tryConfigToUpdate.gameField) {
            if(net.isClosed() && netIsNotMined(net)) return false;
        }
        return true;
    }

    private static boolean netIsNotMined(Net net) {
        return !net.isMined();
    }


    private static void unflagAllNets() {
        tryConfigToUpdate.numberOfUnflaggedMines = 0;
        for(Net net: tryConfigToUpdate.gameField) {
            if(netIsMined(net) && net.isClosed()) net.flag();;
        }
    }

    private static void updateTryIfPutFlag() {
        for(Net netToPutFlag: madeTurn.netsToTurn) {
            netToPutFlag.flag();
            tryConfigToUpdate.numberOfUnflaggedMines--;
        }
    }

    private static  void updateTryIfTakeOffFlag() {
        for(Net netToTakeOffFlag: madeTurn.netsToTurn) {
            netToTakeOffFlag.close();
            tryConfigToUpdate.numberOfUnflaggedMines++;
        }
    }

    private static void updateTryIfPlayerTurn() {
        updateMadeTurnAccordingToUserInput();
        updateTryConfig(madeTurn, tryConfigToUpdate);
    }

    private static void updateMadeTurnAccordingToUserInput() {
        printInfoText();
        setTurnOption();
        int turnLine = scanTurnLine();
        int turnColumn = scanTurnColumn();
        addNetToTurn(turnLine, turnColumn);
    }

    private static void printInfoText() {
        System.out.println("Bot can not make a turn");
        System.out.println("""
						   Enter
						   0: to open net
						   1: to put flag
						   2: to take off flag""");
    }

    private static void setTurnOption() {
        int turnOption = InputScanner.scanInt(a-> a >= 0 && a < 3);
        switch (turnOption) {
            case 0 -> madeTurn.turnOption = TurnOption.OpenNet;
            case 1 -> madeTurn.turnOption = TurnOption.PutFlag;
            case 2 -> madeTurn.turnOption = TurnOption.TakeOffFlag;
        }
    }

    private static int scanTurnLine() {
        System.out.println("Enter line of you ru.ivan.ivanov.menuLogic.gameLogic.turn.turn");
        return InputScanner.scanInt(a-> a>=0 && a<tryConfigToUpdate.fieldHeight);
    }

    private static int scanTurnColumn() {
        System.out.println("Enter column of you ru.ivan.ivanov.menuLogic.gameLogic.turn.turn");
        return InputScanner.scanInt(a-> a>=0 && a<tryConfigToUpdate.fieldWidth);
    }

    private static void addNetToTurn(int turnLine, int turnColumn) {
        int netIndex = turnColumn + turnLine*tryConfigToUpdate.fieldWidth;
        Net netToAdd = tryConfigToUpdate.gameField.get(netIndex);
        madeTurn.netsToTurn.add(netToAdd);
    }
}

