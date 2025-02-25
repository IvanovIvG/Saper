package ru.ivan.ivanov.menuLogic.windows;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.utils.InputScanner;

@Component
@RequiredArgsConstructor
public class GameSettingsConfigurer implements Window {
    //close Windows
    private final Game game;
    private final InputScanner scanner;

    @Override
    public Window runWindowAndGoToNext() {
        System.out.println("Enter field width up to 100");
        int gameFieldWidth = scanner.scanInt((Integer inputNum) -> inputNum > 0 && inputNum < 101);

        System.out.println("Enter field height up to 100");
        int gameFieldHeight = scanner.scanInt((Integer inputNum) -> inputNum>0 && inputNum<101);

        System.out.println("Enter mines number");
        int numberOfMinesOnField = scanner.scanInt((Integer inputNum) ->
                inputNum>0 && inputNum<10000 && inputNum< gameFieldHeight * gameFieldWidth);

        game.setGameFieldSettings(gameFieldWidth, gameFieldHeight, numberOfMinesOnField);
        return game;
    }
}

