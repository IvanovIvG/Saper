package ru.ivan.ivanov.menuLogic;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuLogic.windows.*;
import ru.ivan.ivanov.menuLogic.windows.simpleInputOutputWindows.EntryMenu;
import ru.ivan.ivanov.utils.ApplicationContextHolder;

@Component
public class Menu {
    private Window currentVisibleWindow;

    public Menu(EntryMenu entryMenu) {
        currentVisibleWindow = entryMenu;
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        Menu menu = context.getBean(Menu.class);
        menu.runMenu();
    }

    private void runMenu() throws InterruptedException {
        while(programNotFinished(currentVisibleWindow)) {
            currentVisibleWindow = currentVisibleWindow.runWindowAndGoToNext();
        }
    }

    private boolean programNotFinished(Window currentVisibleWindow) {
        boolean programFinished = currentVisibleWindow == null;
        return !programFinished;
    }


}
