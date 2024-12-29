package ru.ivan.ivanov.menuLogic.windows;

import org.springframework.stereotype.Component;

/**
 * This class is Stub for closing program
 */
@Component
public class EndProgramWindowStub implements Window {

    @Override
    public Window runWindowAndGoToNext() throws InterruptedException {
        throw new InterruptedException();
    }
}
