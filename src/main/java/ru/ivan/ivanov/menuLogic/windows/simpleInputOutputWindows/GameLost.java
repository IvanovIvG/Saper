package ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuLogic.windows.Window;

@Component
public class GameLost extends SimpleInputOutputWindow {
    // close Windows
    private final EntryMenu entryMenu;

    @Autowired
    public GameLost(EntryMenu entryMenu) {
        super("""
                Sorry, you've lost!
                1: to menu""",
               (Integer inputInt) -> inputInt == 1
        );

        this.entryMenu = entryMenu;
    }

    @Override
    protected Window goToNextWindow(int inputInt) {
        return entryMenu;
    }
}
