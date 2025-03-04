package ru.ivan.ivanov.gameCreator.gameConfig;

import lombok.Getter;
import ru.ivan.ivanov.gameCreator.gameFactory.RandomFieldGameFactory;

/**
 * Configuration of game with random generated field.
 *
 * @author Ivan Ivanov
 **/
@Getter
public class RandomFieldGameConfig extends GameConfig {
    private final int fieldWidth;
    private final int fieldHeight;
    private final int mineNumber;

    public RandomFieldGameConfig(int fieldWidth, int fieldHeight, int mineNumber) {
        super(RandomFieldGameFactory.class);
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.mineNumber = mineNumber;
    }
}
