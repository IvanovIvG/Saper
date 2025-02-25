package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.gameTry.GameTry;
import ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows.GameLost;
import ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows.GameWin;

/**
 * This window runs game.
 *
 *  @author Ivan Ivanov
 **/
@Component
public class Game implements Window {
    // close Windows
    private final GameWin gameWin;
    private final GameLost gameLost;

    private GameTry gameTry;

    @Override
    public Window runWindowAndGoToNext() {
        boolean gameResult = gameTry.playGameStub();
        if(gameWon(gameResult)) {
            return gameWin;
        }
        else {
            return gameLost;
        }
    }

    private boolean gameWon(boolean gameResult) {
        return gameResult;
    }

    @Autowired
    public Game(GameWin gameWin, GameLost gameLost) {
        this.gameWin = gameWin;
        this.gameLost = gameLost;
    }

    @Autowired
    @Lazy
    public void setGameTry(GameTry gameTry) {
        this.gameTry = gameTry;
    }
}
