package ru.ivan.ivanov.botTests.closeNetsInclusion;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.CloseNetsInclusionPattern;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.FlagAllCloseNetsPattern;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.OpenAllCloseNetsPattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for CloseNetsInclusionPattern
 *
 * @author Ivan Ivanov
 **/
class CloseNetsInclusionPatternTest {
    private final TestContextCreator contextCreator = new TestContextCreator();

    @Test
    public void openNetsWhenCloseNetsHasInclusion(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/closeNetsInclusion/openNets.txt";
        contextCreator.createContext(textContextFileName);
        CloseNetsInclusionPattern pattern = getPattern();
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        Set<Net> expectedTurnNets = Set.copyOf(expectedTurn.getNetsToTurn());
        Set<Net> actualTurnNets = Set.copyOf(madeTurn.getNetsToTurn());
        assertEquals(expectedTurnNets, actualTurnNets);
    }

    @Test
    public void flagNetsWhenCloseNetsHasInclusion(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/closeNetsInclusion/flagNets.txt";
        contextCreator.createContext(textContextFileName);
        CloseNetsInclusionPattern pattern = getPattern();
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        Set<Net> expectedTurnNets = Set.copyOf(expectedTurn.getNetsToTurn());
        Set<Net> actualTurnNets = Set.copyOf(madeTurn.getNetsToTurn());
        assertEquals(expectedTurnNets, actualTurnNets);
    }

    @Test
    public void testBigField(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/closeNetsInclusion/bigField.txt";
        contextCreator.createContext(textContextFileName);
        CloseNetsInclusionPattern pattern = getPattern();
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        Set<Net> expectedTurnNets = Set.copyOf(expectedTurn.getNetsToTurn());
        Set<Net> actualTurnNets = Set.copyOf(madeTurn.getNetsToTurn());
        assertEquals(expectedTurnNets, actualTurnNets);
    }

    public CloseNetsInclusionPattern getPattern(){
        OpenAllCloseNetsPattern openAllCloseNetsPattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());
        FlagAllCloseNetsPattern flagAllCloseNetsPattern = new FlagAllCloseNetsPattern(contextCreator.getFiledInfo());
        return new CloseNetsInclusionPattern(contextCreator.getFiledInfo(),
                openAllCloseNetsPattern, flagAllCloseNetsPattern);
    }

}