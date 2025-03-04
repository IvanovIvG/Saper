package ru.ivan.ivanov.gameCreator.gameConfig;

import lombok.Getter;
import ru.ivan.ivanov.gameCreator.gameFactory.GameFactory;
import ru.ivan.ivanov.gameCreator.gameFactory.PreGeneratedFieldGameFactory;

/**
 * Configuration of game with pregenerated game field.
 *
 * @author Ivan Ivanov
 **/
@Getter
public class PreGeneratedFieldGameConfig extends GameConfig {
    private final String configFileName;

    public PreGeneratedFieldGameConfig() {
        super(PreGeneratedFieldGameFactory.class);
        configFileName = "src/main/resources/gameConfig.txt";
    }

    public PreGeneratedFieldGameConfig(String configFileName) {
        super(PreGeneratedFieldGameFactory.class);
        this.configFileName = configFileName;
    }
}
