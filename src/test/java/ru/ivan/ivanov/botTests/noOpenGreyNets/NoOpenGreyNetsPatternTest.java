package ru.ivan.ivanov.botTests.noOpenGreyNets;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameLogic.bot.patterns.NoOpenGreyNetsPattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests NoOpenGreyNetsPattern logic
 *
 * @author Ivan Ivanov
 **/

class NoOpenGreyNetsPatternTest {
    private final TestContextCreator contextCreator = new TestContextCreator();

    @Test
    public void patternOpensNet(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/noOpenGreyNets/NoNetsToOpen.txt";
        contextCreator.createContext(textContextFileName);
        NoOpenGreyNetsPattern pattern = new NoOpenGreyNetsPattern(contextCreator.getFiledInfo());
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(madeTurn.getTurnOption(), expectedTurn.getTurnOption());
    }
}