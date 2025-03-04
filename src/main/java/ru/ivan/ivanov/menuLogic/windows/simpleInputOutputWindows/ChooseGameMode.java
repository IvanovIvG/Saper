package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameCreator.gameConfig.PreGeneratedFieldGameConfig;
import ru.ivan.ivanov.gameCreator.gameConfig.RandomFieldGameConfig;
import ru.ivan.ivanov.gameCreator.GameCreator;
import ru.ivan.ivanov.menuLogic.windows.GameWindow;
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
    private final GameWindow gameWindow;
    private final SetCustomGameSettings setCustomGameSettings;

    private final GameCreator gameCreator;

    @Autowired
    public ChooseGameMode(EntryMenu entryMenu, GameWindow gameWindow,
                          SetCustomGameSettings setCustomGameSettings,
                          GameCreator gameCreator){
        super("""
          Choose game mode:
          1: novice
          2: amateur
          3: professional
          4: custom
          5: saved field
          0: go back""",
          (Integer inputInt) ->
            inputInt==0 || inputInt==1 || inputInt==2 || inputInt==3 || inputInt==4
        );

        this.entryMenu = entryMenu;
        this.gameWindow = gameWindow;
        this.setCustomGameSettings = setCustomGameSettings;
        this.gameCreator = gameCreator;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> { //novice game mode
                gameCreator.configure(
                        new RandomFieldGameConfig(9, 9, 10));
                yield gameWindow;
            }
            case 2 -> { //amateur game mode
                gameCreator.configure(
                        new RandomFieldGameConfig(16, 16, 40));
                yield gameWindow;
            }
            case 3 -> { //professional game mode
                gameCreator.configure(
                        new RandomFieldGameConfig(16, 30, 99));
                yield gameWindow;
            }
            case 4 -> setCustomGameSettings;
            case 5 -> { //game mode with preSaved game field
                gameCreator.configure(
                        new PreGeneratedFieldGameConfig());
                yield gameWindow;
            }
            case 0 -> entryMenu;
            default -> null;
        };
    }
}


