package ru.ivan.ivanov.gameCreator.gameFactory;

import ru.ivan.ivanov.gameCreator.gameConfig.GameConfig;
import ru.ivan.ivanov.gameData.GameData;

/**
 * Produce and fill in GameData fields depends on gameConfig data.
 *
 * @author Ivan Ivanov
 **/
public abstract class GameFactory {
    public void createGame(GameConfig gameConfig, GameData gameData){
        if(!supportsGameConfig(gameConfig)) throw new RuntimeException("Invalid gameConfig instance");
        fillInGameData(gameConfig, gameData);
    }

    protected abstract boolean supportsGameConfig(GameConfig gameConfig);
    protected abstract void fillInGameData(GameConfig gameConfig, GameData gameData);
}
