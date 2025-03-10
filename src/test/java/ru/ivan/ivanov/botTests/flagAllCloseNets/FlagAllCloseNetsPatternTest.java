package ru.ivan.ivanov.botTests.flagAllCloseNets;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.FlagAllCloseNetsPattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test for FlagAllCloseNetsPattern
 *
 * @author Ivan Ivanov
 **/
class FlagAllCloseNetsPatternTest {
    private final TestContextCreator contextCreator = new TestContextCreator();

    @Test
    public void FlagNetsWhenClosedNetsNumberEqualToMineNumber(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/flagAllCloseNets/mineAndCloseNetsNumberEqual.txt";
        contextCreator.createContext(textContextFileName);
        FlagAllCloseNetsPattern pattern = new FlagAllCloseNetsPattern(contextCreator.getFiledInfo());
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        Set<Net> expectedTurnNets = Set.copyOf(expectedTurn.getNetsToTurn());
        Set<Net> actualTurnNets = Set.copyOf(madeTurn.getNetsToTurn());
        assertEquals(expectedTurnNets, actualTurnNets);
    }

    @Test
    public void ReturnsNullWhenClosedNetsNumberNotEqualToMineNumber(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/flagAllCloseNets/mineAndCloseNetsNumberNotEqual.txt";
        contextCreator.createContext(textContextFileName);
        FlagAllCloseNetsPattern pattern = new FlagAllCloseNetsPattern(contextCreator.getFiledInfo());

        Turn madeTurn = pattern.tryPattern();

        assertNull(madeTurn);
    }
}