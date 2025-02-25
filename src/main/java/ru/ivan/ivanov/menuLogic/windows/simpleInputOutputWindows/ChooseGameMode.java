package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.gameTry.GameProperties;
import ru.ivan.ivanov.menuLogic.windows.Game;
import ru.ivan.ivanov.menuLogic.windows.SetCustomGameSettings;
import ru.ivan.ivanov.menuLogic.windows.Window;

/**
 * This window is used to choose game modes.
 *
 *  @author Ivan Ivanov
 **/
@Component
public class ChooseGameMode extends SimpleInputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;
    private final Game game;
    private final SetCustomGameSettings setCustomGameSettings;

    private final GameProperties gameProperties;

    @Autowired
    public ChooseGameMode(EntryMenu entryMenu, Game game,
                          SetCustomGameSettings setCustomGameSettings,
                          GameProperties gameProperties){
        super("""
          Choose game mode:
          1: novice
          2: amateur
          3: professional
          4: custom
          0: go back""",
          (Integer inputInt) ->
            inputInt==0 || inputInt==1 || inputInt==2 || inputInt==3 || inputInt==4
        );

        this.entryMenu = entryMenu;
        this.game = game;
        this.setCustomGameSettings = setCustomGameSettings;
        this.gameProperties = gameProperties;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> { //novice game mode
                gameProperties.setProperties(9, 9 , 10);
                yield game;
            }
            case 2 -> { //amateur game mode
                gameProperties.setProperties(16, 16 , 40);
                yield game;
            }
            case 3 -> { //professional game mode
                gameProperties.setProperties(16, 30 , 99);
                yield game;
            }
            case 4 -> setCustomGameSettings;
            case 0 -> entryMenu;
            default -> null;
        };
    }
}


