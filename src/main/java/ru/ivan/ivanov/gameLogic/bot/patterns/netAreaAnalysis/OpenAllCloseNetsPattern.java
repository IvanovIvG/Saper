package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
@Order(2)
public class OpenAllCloseNetsPattern extends NetAreaAnalysisAbstractPattern{

    public OpenAllCloseNetsPattern(FieldInfo fieldInfo) {
        super(fieldInfo);
    }

    @Override
    protected Turn tryPatternOnNet() {
        if(allCloseMinesAreFlagged()) {
            return openAllCloseClosedNets();
        }
        return null;
    }

    private boolean allCloseMinesAreFlagged() {
        return flaggedCloseNets.size() == mineNumber;
    }

    private Turn openAllCloseClosedNets() {
        TurnOption turnOption = TurnOption.OpenNet;
        List<Net> netsToTurn = new ArrayList<>(closedCloseNets);
        return new Turn(turnOption, netsToTurn);
    }
}
