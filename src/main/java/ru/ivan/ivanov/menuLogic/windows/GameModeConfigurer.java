package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameModeConfigurer extends InputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;
    private final Game game;
    private final GameSettingsConfigurer gameSettingsConfigurer;

    @Autowired
    public GameModeConfigurer(EntryMenu entryMenu, Game game, GameSettingsConfigurer gameSettingsConfigurer){
        super("""
          Choose game mode
          1: novice
          2: amateur
          3: professional
          4: custom
          0: go back""",
          (int inputInt) ->
            inputInt==0 || inputInt==1 || inputInt==2 || inputInt==3 || inputInt==4);

        this.entryMenu = entryMenu;
        this.game = game;
        this.gameSettingsConfigurer = gameSettingsConfigurer;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return switch (inputInt) {
            case 1 -> { //novice game mode
                game.setGameFieldSettings(9, 9, 10);
                yield game;
            }
            case 2 -> { //amateur game mode
                game.setGameFieldSettings(16, 16, 40);
                yield game;
            }
            case 3 -> { //professional game mode
                game.setGameFieldSettings(16, 30, 99);
                yield game;
            }
            case 4 -> gameSettingsConfigurer;
            case 0 -> entryMenu;
            default -> null;
        };
    }
}


