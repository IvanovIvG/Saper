package ru.ivan.ivanov.gameCreator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.gameConfig.GameConfig;
import ru.ivan.ivanov.gameCreator.gameFactory.GameFactory;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.utils.ApplicationContextHolder;

/**
 * Used for configuring and game creation.
 * <p>
 * GameFactory produce and fill in GameData fields. it uses data from gameConfig.
 * Certain implementation of GameFactory depends on gameConfig
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class GameCreator {
    private final GameData gameData;
    @Getter
    private boolean configurated = false;
    private GameConfig gameConfig;
    private GameFactory gameFactory;

    public void configure(GameConfig gameConfig){
        configurated = true;
        this.gameConfig = gameConfig;
        Class<? extends GameFactory> gameFactoryClass = gameConfig.getGameFactoryClass();
        gameFactory = ApplicationContextHolder.getApplicationContext().getBean(gameFactoryClass);
    }

    public void createGame(){
        if(!configurated) throw new RuntimeException("Game creation without configuration");
        gameFactory.createGame(gameConfig, gameData);
    }

}
