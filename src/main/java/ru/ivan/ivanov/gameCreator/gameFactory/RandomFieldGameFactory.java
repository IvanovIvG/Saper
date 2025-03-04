package ru.ivan.ivanov.gameCreator.gameFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.gameConfig.GameConfig;
import ru.ivan.ivanov.gameCreator.gameConfig.RandomFieldGameConfig;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.GameState;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.List;

/**
 * Factory for randomly generated game field.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class RandomFieldGameFactory extends GameFactory{
    private final GameFieldCreator gameFieldCreator;

    @Override
    protected boolean supportsGameConfig(GameConfig gameConfig) {
        return gameConfig instanceof RandomFieldGameConfig;
    }

    @Override
    protected void fillInGameData(GameConfig gameConfig, GameData gameData) {
        RandomFieldGameConfig config = (RandomFieldGameConfig) gameConfig;

        GameState gameState = GameState.GameGoOn;
        int fieldWidth = config.getFieldWidth();
        int fieldHeight = config.getFieldHeight();
        int mineNumber = config.getMineNumber();
        List<Net> gameField = gameFieldCreator.createRandomGameField(fieldWidth, fieldHeight, mineNumber);

        gameData.setGameField(gameField);
        gameData.setGameState(gameState);
        gameData.setFieldWidth(fieldWidth);
        gameData.setFieldHeight(fieldHeight);
        gameData.setMineNumber(mineNumber);
        gameData.setNumberOfUnflaggedMines(mineNumber);
    }
}
