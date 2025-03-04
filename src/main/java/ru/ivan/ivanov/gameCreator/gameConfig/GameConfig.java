package ru.ivan.ivanov.gameCreator.gameConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.ivan.ivanov.gameCreator.gameFactory.GameFactory;

/**
 * Game configuration.
 * <p>
 * Contains information which GameFactory implementation needs to process this data.
 *
 * @author Ivan Ivanov
 **/
@RequiredArgsConstructor
@Getter
public abstract class GameConfig {
    private final Class<? extends GameFactory> gameFactoryClass;
}
