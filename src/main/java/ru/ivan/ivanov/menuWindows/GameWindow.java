package ru.ivan.ivanov.menuWindows;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.Game;
import ru.ivan.ivanov.menuWindows.simpleInputOutputWindows.GameLost;
import ru.ivan.ivanov.menuWindows.simpleInputOutputWindows.GameWin;

/**
 * This window runs game.
 * <p>
 * GameTry class is lazily initialized when runWindowAndGoToNext() called.
 *
 *  @author Ivan Ivanov
 **/
@Component
public class GameWindow implements Window {
    // close Windows
    private final GameWin gameWin;
    private final GameLost gameLost;

    private final Game game;

    public GameWindow(GameWin gameWin, GameLost gameLost, Game game) {
        this.gameWin = gameWin;
        this.gameLost = gameLost;
        this.game = game;
    }

    @Override
    public Window runWindowAndGoToNext() {
        boolean gameResult = game.playGame();
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
}
