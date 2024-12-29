package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameLost extends InputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;

    @Autowired
    public GameLost(EntryMenu entryMenu) {
        super("""
                Sorry, you've lost!
                1: to menu""",
               (int inputInt) -> inputInt == 1);

        this.entryMenu = entryMenu;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return entryMenu;
    }
}
