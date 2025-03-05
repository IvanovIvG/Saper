package ru.ivan.ivanov.botTests.openRandomNet;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.botTests.testContextCreator.TestContextCreator;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.patterns.OpenRandomNetPattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ivan Ivanov
 **/
class OpenRandomNetPatternTest {
    private final TestContextCreator contextCreator = new TestContextCreator();

    @Test
    public void OpensOutsideNet(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/openRandomNet/openOutsideNet.txt";
        contextCreator.createContext(textContextFileName);
        OpenRandomNetPattern pattern = new OpenRandomNetPattern(contextCreator.getFiledInfo());
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        boolean actualTurnNetsContainsInExpected = possibleTurnNetsContainsActualTurnNets(expectedTurn, madeTurn);
        assertTrue(actualTurnNetsContainsInExpected);
    }

    @Test
    public void flagCentralNets(){
        String textContextFileName =
                "src/test/java/ru/ivan/ivanov/botTests/openRandomNet/flagCentralNets";
        contextCreator.createContext(textContextFileName);
        OpenRandomNetPattern pattern = new OpenRandomNetPattern(contextCreator.getFiledInfo());
        Turn expectedTurn = contextCreator.getExpectedTurn();

        Turn madeTurn = pattern.tryPattern();

        assertEquals(expectedTurn.getTurnOption(), madeTurn.getTurnOption());
        boolean actualAndExpectedTurnNetsEqual = possibleTurnNetsContainsActualTurnNets(expectedTurn, madeTurn);
        assertTrue(actualAndExpectedTurnNetsEqual);
    }

    private boolean possibleTurnNetsContainsActualTurnNets(Turn expectedTurn, Turn madeTurn) {
        Set<Net> possibleTurnNets = Set.copyOf(expectedTurn.getNetsToTurn());
        Set<Net> actualTurnNet = Set.copyOf(madeTurn.getNetsToTurn());
        return possibleTurnNets.equals(actualTurnNet);
    }

}