package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.gameTry.GameTry;

@Component
public class Game implements Window {
    // close Windows
    private final GameWin gameWin;
    private final GameLost gameLost;

    private int fieldWidth;
    private int fieldHeight;
    private int mineNumber;

    @Autowired
    public Game(GameWin gameWin, GameLost gameLost) {
        this.gameWin = gameWin;
        this.gameLost = gameLost;
    }

    @Override
    public Window runWindowAndGoToNext() {

        GameTry gameTry = new GameTry(fieldWidth, fieldHeight, mineNumber);
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

    protected void setGameFieldSettings(int width, int height, int mines) {
        fieldWidth = width;
        fieldHeight = height;
        mineNumber = mines;
    }
}
