package ru.ivan.ivanov.gameCreator.gameFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.gameConfig.GameConfig;
import ru.ivan.ivanov.gameCreator.gameConfig.PreGeneratedFieldGameConfig;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.GameState;
import ru.ivan.ivanov.gameData.net.Net;

import java.io.IOException;
import java.util.List;

/**
 * Factory for game field generated from file.
 * <p>
 * File contains information about mines position on filed.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class PreGeneratedFieldGameFactory extends GameFactory{
    private final FileReader fileReader;
    private final GameFieldCreator gameFieldCreator;

    @Override
    protected boolean supportsGameConfig(GameConfig gameConfig) {
        return gameConfig instanceof PreGeneratedFieldGameConfig;
    }

    @Override
    protected void fillInGameData(GameConfig gameConfig, GameData gameData) {
        PreGeneratedFieldGameConfig config = (PreGeneratedFieldGameConfig) gameConfig;
        String configFileName = config.getConfigFileName();

        GameState gameState = GameState.GameGoOn;
        try {
            fileReader.readFile(configFileName);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file");
        }
        int fieldWidth = fileReader.getFieldWidth();
        int fieldHeight = fileReader.getFieldHeight();
        int mineNumber = fileReader.getMineNumber();
        Boolean[] lineField = fileReader.getLineField();
        List<Net> gameField = gameFieldCreator.createGameFieldFromLineField(fieldWidth, fieldHeight,lineField);

        gameData.setGameField(gameField);
        gameData.setGameState(gameState);
        gameData.setFieldWidth(fieldWidth);
        gameData.setFieldHeight(fieldHeight);
        gameData.setMineNumber(mineNumber);
        gameData.setNumberOfUnflaggedMines(mineNumber);
    }
}
