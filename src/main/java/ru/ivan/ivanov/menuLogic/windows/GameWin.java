package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameWin extends InputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;

    @Autowired
    public GameWin(EntryMenu entryMenu) {
        super("""
                Congratulations, you won!
                1: to menu""",
               (int inputNum) -> inputNum == 1);

        this.entryMenu = entryMenu;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return entryMenu;
    }
}
