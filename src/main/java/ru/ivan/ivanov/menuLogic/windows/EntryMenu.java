package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryMenu extends InputOutputWindow {
    // close Windows
    // GameModeConfigurer made not final to make possible EntryMenu bean initialization
    private GameModeConfigurer gameModeConfigurer;
    private final EndProgramWindowStub endProgramWindowStub;

    @Autowired
    public EntryMenu(EndProgramWindowStub endProgramWindowStub){
        super("""
          Welcome Saper game!
          1: start game
          0: quit game""",
          (int inputInt) -> inputInt==0 || inputInt==1);
        this.endProgramWindowStub = endProgramWindowStub;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> gameModeConfigurer;
            case 0 -> endProgramWindowStub;
            default -> null; //unreached statement
        };
    }

    @Autowired
    public void setGameModeConfigurer(GameModeConfigurer gameModeConfigurer) {
        this.gameModeConfigurer = gameModeConfigurer;
    }
}

