package ru.ivan.ivanov.gameLogic.gameTry.tryConfig;

import ru.ivan.ivanov.gameLogic.net.Net;
import java.io.IOException;
import java.util.List;

public class TryConfig {
    public List<Net> gameField;
    public GameState gameState;
    public final int fieldWidth;
    public final int fieldHeight;
    public final int mineNumber;
    public int numberOfUnflaggedMines;

    private TryConfig(int fieldWidth, int fieldHeight, int mineNumber) {
        gameState = GameState.GameGoOn;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.mineNumber = mineNumber;
        numberOfUnflaggedMines = mineNumber;
    }

    public static TryConfig getTryConfigWithRandomField(int fieldWidth, int fieldHeight, int mineNumber){
        TryConfig tryConfig = new TryConfig(fieldWidth, fieldHeight, mineNumber);

        GameFieldCreator gameFieldCreator = new GameFieldCreator(fieldWidth, fieldHeight, mineNumber);
        tryConfig.gameField = gameFieldCreator.createRandomGameField();

        return tryConfig;
    }

    public static TryConfig getTryConfigFromFile(String SavedFieldFilePath) throws IOException {
        GameFileConfig gameFileConfig = GameFileConfig.getGameFileConfig(SavedFieldFilePath);
        return getTryConfigFromGameFileConfig(gameFileConfig);
    }

    private static TryConfig getTryConfigFromGameFileConfig(GameFileConfig gameFileConfig) {
        int fieldWidth = gameFileConfig.getFieldWidth();
        int fieldHeight = gameFileConfig.getFieldHeight();
        int mineNumber = gameFileConfig.getMineNumber();

        TryConfig tryConfig = new TryConfig(fieldWidth, fieldHeight, mineNumber);
        fillInGameFieldInTryConfig(gameFileConfig, tryConfig);

        return tryConfig;
    }

    private static void fillInGameFieldInTryConfig(GameFileConfig gameFileConfig, TryConfig tryConfig) {
        GameFieldCreator gameFieldCreator = new GameFieldCreator(tryConfig.fieldWidth, tryConfig.fieldHeight, tryConfig.mineNumber);
        Boolean[] lineFiled = gameFileConfig.getLineField();
        tryConfig.gameField = gameFieldCreator.createGameFieldFromLineField(lineFiled);
    }
}
