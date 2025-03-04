package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuLogic.windows.Window;

/**
 * This is app's entry menu window.
 *
 *  @author Ivan Ivanov
 **/
@Component
public class EntryMenu extends SimpleInputOutputWindow {
    // close Windows
    // GameModeConfigurer made not final to make possible EntryMenu bean initialization
    private ChooseGameMode chooseGameMode;

    public EntryMenu(){
        super("""
          Welcome Saper!
          1: start gameWindow
          0: quit gameWindow""",
          (Integer inputInt) -> inputInt==0 || inputInt==1
        );
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> chooseGameMode;
            case 0 -> null;
            default -> null; //unreached statement
        };
    }

    @Autowired
    public void setGameModeConfigurer(ChooseGameMode chooseGameMode) {
        this.chooseGameMode = chooseGameMode;
    }
}

