package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.saperUtils.inputScanner.InputScanner;

@Component
public class GameSettingsConfigurer extends InputOutputWindow {
    //close Windows
    private final Game game;

    @Autowired
    public GameSettingsConfigurer(Game game) {
        super();
        this.game = game;
    }

    @Override
    public Window runWindowAndGoToNext() {
        printText("Enter field width up to 100");
        int gameFieldWidth = InputScanner.scanInt((int inputNum) -> inputNum > 0 && inputNum < 101);
        printText("Enter field height up to 100");
        int gameFieldHeight = InputScanner.scanInt((int inputNum) -> inputNum>0 && inputNum<101);
        printText("Enter mines number");
        int numberOfMinesOnField = InputScanner.scanInt((int inputNum) -> inputNum>0 && inputNum<10000 && inputNum< gameFieldHeight * gameFieldWidth);
        game.setGameFieldSettings(gameFieldWidth, gameFieldHeight, numberOfMinesOnField);
        return goToNextWindow(scannedInt);
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return game;
    }
}

