package ru.ivan.ivanov.gameLogic.gameDataUpdater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.GameState;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.gameDataUpdater.netOpener.NetOpener;
import ru.ivan.ivanov.gameLogic.turn.Turn;

/**
 * Updates game data depends on turn has done.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class GameDataUpdater {
    private final GameData gameData;
    private final NetOpener netOpener;
    private final PlayerTurnScanner playerTurnScanner;

    private Turn madeTurn;

    public void update(Turn turn) {
        madeTurn = turn;

        switch (madeTurn.getTurnOption()) {
            case OpenNet -> updateIfNetOpen();
            case PutFlag -> updateIfPutFlag();
            case TakeOffFlag -> updateIfTakeOffFlag();
            case PlayerTurn -> updateIfPlayerTurn();
        }
    }

    private void updateIfNetOpen() {
        for(Net netToOpen: madeTurn.getNetsToTurn()) {
            if(netIsMined(netToOpen)) {
                openMinedNet(netToOpen);
            }
            else {
                openNotMinedNet(netToOpen);
            }
        }
    }

    private boolean netIsMined(Net netToOpen) {
        return netToOpen.isMined();
    }

    private void openMinedNet(Net netToOpen) {
        netToOpen.explose();
        gameData.setGameState(GameState.GameLost);
        for(Net net: gameData.getGameField()) {
            if(net.isClosed()) net.open();
        }
    }

    private void openNotMinedNet(Net netToOpen) {
        netOpener.openNet(netToOpen);
        if(gameWon()) {
            gameData.setGameState(GameState.GameWin);
            flagAllMinedNets();
        }
    }

    private boolean gameWon() {
        return AllPossibleNetsOpened();
    }

    private boolean AllPossibleNetsOpened() {
        for (Net net : gameData.getGameField()) {
            if(net.isClosed() && netIsNotMined(net)) return false;
        }
        return true;
    }

    private boolean netIsNotMined(Net net) {
        return !net.isMined();
    }


    private void flagAllMinedNets() {
        gameData.setNumberOfUnflaggedMines(0);
        for(Net net: gameData.getGameField()) {
            if(netIsMined(net) && net.isClosed())
                net.flag();
        }
    }

    private void updateIfPutFlag() {
        for(Net netToPutFlag: madeTurn.getNetsToTurn()) {
            netToPutFlag.flag();
            gameData.flagOneNet();
        }
    }

    private void updateIfTakeOffFlag() {
        for(Net netToTakeOffFlag: madeTurn.getNetsToTurn()) {
            netToTakeOffFlag.close();
            gameData.unFlagOneNet();
        }
    }

    private void updateIfPlayerTurn() {
        Turn turn = playerTurnScanner.makeTurn();
        update(turn);
    }
}
