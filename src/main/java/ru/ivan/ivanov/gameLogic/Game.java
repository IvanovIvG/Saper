package ru.ivan.ivanov.gameLogic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.GameCreator;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.GameState;
import ru.ivan.ivanov.gameLogic.bot.Bot;
import ru.ivan.ivanov.gameLogic.gameDataUpdater.GameDataUpdater;
import ru.ivan.ivanov.gameLogic.turn.Turn;

/**
 * Main game function
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class Game {
    private final GameCreator gameCreator;
    private final Bot bot;
    private final GameDataUpdater gameDataUpdater;
    private final FieldDrawer fieldDrawer;
    private final GameData gameData;

    public boolean playGame() {
        gameCreator.createGame();
        while(gameGoOn()) {
            Turn turn = bot.makeTurn();
            gameDataUpdater.update(turn);
            fieldDrawer.showField();
        }
        return gameWon();
    }

    private boolean gameGoOn() {
        return gameData.getGameState() == GameState.GameGoOn;
    }

    private boolean gameWon() {
        return gameData.getGameState() == GameState.GameWin;
    }
}
