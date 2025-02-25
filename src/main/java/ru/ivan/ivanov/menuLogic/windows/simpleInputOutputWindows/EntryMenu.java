package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuLogic.windows.Window;

@Component
public class EntryMenu extends SimpleInputOutputWindow {
    // close Windows
    // GameModeConfigurer made not final to make possible EntryMenu bean initialization
    private GameModeConfigurer gameModeConfigurer;

    public EntryMenu(){
        super("""
          Welcome Saper game!
          1: start game
          0: quit game""",
          (Integer inputInt) -> inputInt==0 || inputInt==1
        );
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> gameModeConfigurer;
            case 0 -> null;
            default -> null; //unreached statement
        };
    }

    @Autowired
    public void setGameModeConfigurer(GameModeConfigurer gameModeConfigurer) {
        this.gameModeConfigurer = gameModeConfigurer;
    }
}

