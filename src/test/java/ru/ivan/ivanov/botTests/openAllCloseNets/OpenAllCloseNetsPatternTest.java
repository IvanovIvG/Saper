package ru.ivan.ivanov.botTests.openAllCloseNets;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.OpenAllCloseNetsPattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ivan Ivanov
 **/
class OpenAllCloseNetsPatternTest {
    private final TestContextCreator contextCreator = new TestContextCreator();

    @Test
    public void OpenNetsWhenAllMinedNetsAreFlagged(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/openAllCloseNets/CloseMinedNetsFlagged.txt";
        contextCreator.createContext(textContextFileName);
        OpenAllCloseNetsPattern pattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(madeTurn.getTurnOption(), expectedTurn.getTurnOption());
        assertEquals(Set.copyOf(madeTurn.getNetsToTurn()), Set.copyOf(expectedTurn.getNetsToTurn()));
    }

    @Test
    public void NotAllMinedFlaggedNetsReturnsNull(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/openAllCloseNets/NotAllCloseMinedNetsFlagged.txt";
        contextCreator.createContext(textContextFileName);
        OpenAllCloseNetsPattern pattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());

        Turn madeTurn = pattern.tryPattern();

        assertNull(madeTurn);
    }

    @Test
    public void NoNetsToOpenReturnsNull(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/openAllCloseNets/NoNetsToOpen.txt";
        contextCreator.createContext(textContextFileName);
        OpenAllCloseNetsPattern pattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());

        Turn madeTurn = pattern.tryPattern();

        assertNull(madeTurn);
    }
}