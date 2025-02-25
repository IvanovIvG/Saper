package ru.ivan.ivanov.menuLogic.windows;

/**
 * Contract for app's windows.
 * <p>
 * Windows must do something and return link to next window to run.
 *
 * @author Ivan Ivanov
 **/
public interface Window {
    Window runWindowAndGoToNext() throws InterruptedException;
}

