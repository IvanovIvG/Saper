package ru.ivan.ivanov.menuLogic.windows;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.GameCreator;
import ru.ivan.ivanov.gameCreator.gameConfig.RandomFieldGameConfig;
import ru.ivan.ivanov.utils.InputScanner;

/**
 * This window sets custom game settings.
 *
 *  @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class SetCustomGameSettings implements Window {
    //close Windows
    private final GameWindow gameWindow;

    private final InputScanner scanner;
    private final GameCreator gameCreator;

    @Override
    public Window runWindowAndGoToNext() {
        System.out.println("Enter field width up to 100");
        int gameFieldWidth = scanner.scanInt((Integer inputNum) -> inputNum > 0 && inputNum < 101);

        System.out.println("Enter field height up to 100");
        int gameFieldHeight = scanner.scanInt((Integer inputNum) -> inputNum>0 && inputNum<101);

        System.out.println("Enter mines number");
        int numberOfMinesOnField = scanner.scanInt((Integer inputNum) ->
                inputNum>0 && inputNum<10000 && inputNum< gameFieldHeight * gameFieldWidth);

        gameCreator.configure(
                new RandomFieldGameConfig(gameFieldWidth, gameFieldHeight, numberOfMinesOnField));
        return gameWindow;
    }
}

