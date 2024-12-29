package ru.ivan.ivanov.menuLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.menuLogic.windows.*;
import ru.ivan.ivanov.saperUtils.ApplicationContextHolder;

@Component
public class Menu {
    private Window currentVisibleWindow;

    @Autowired
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
        boolean programFinished = currentVisibleWindow instanceof EndProgramWindowStub;
        return !programFinished;
    }


}
