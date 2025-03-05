package ru.ivan.ivanov.menuWindows.simpleInputOutputWindows;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuWindows.Window;
import ru.ivan.ivanov.utils.InputScanner;

/**
 * This window is shown when game is won.
 *
 *  @author Ivan Ivanov
 **/
@Component
public class GameWin extends SimpleInputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;

    public GameWin(EntryMenu entryMenu, InputScanner scanner) {
        super("""
                Congratulations, you won!
                1: to menu""",
               (Integer inputNum) -> inputNum == 1
        );

        this.entryMenu = entryMenu;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return entryMenu;
    }
}
