package ru.ivan.ivanov.botTests.closeNetsInclusion;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameLogic.bot.patterns.NoOpenGreyNetsPattern;
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
        OpenAllCloseNetsPattern openAllCloseNetsPattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());
        FlagAllCloseNetsPattern flagAllCloseNetsPattern = new FlagAllCloseNetsPattern(contextCreator.getFiledInfo());
        CloseNetsInclusionPattern pattern = new CloseNetsInclusionPattern(contextCreator.getFiledInfo(),
                openAllCloseNetsPattern, flagAllCloseNetsPattern);
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(madeTurn.getTurnOption(), expectedTurn.getTurnOption());
        assertEquals(Set.copyOf(madeTurn.getNetsToTurn()), Set.copyOf(expectedTurn.getNetsToTurn()));
    }

    @Test
    public void flagNetsWhenCloseNetsHasInclusion(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/closeNetsInclusion/flagNets.txt";
        contextCreator.createContext(textContextFileName);
        OpenAllCloseNetsPattern openAllCloseNetsPattern = new OpenAllCloseNetsPattern(contextCreator.getFiledInfo());
        FlagAllCloseNetsPattern flagAllCloseNetsPattern = new FlagAllCloseNetsPattern(contextCreator.getFiledInfo());
        CloseNetsInclusionPattern pattern = new CloseNetsInclusionPattern(contextCreator.getFiledInfo(),
                openAllCloseNetsPattern, flagAllCloseNetsPattern);
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(madeTurn.getTurnOption(), expectedTurn.getTurnOption());
        assertEquals(Set.copyOf(madeTurn.getNetsToTurn()), Set.copyOf(expectedTurn.getNetsToTurn()));
    }

}