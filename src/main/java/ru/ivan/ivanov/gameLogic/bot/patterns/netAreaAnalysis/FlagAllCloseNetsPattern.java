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
 *  Flag all close nets when all close nets are mined
 *
 * @author Ivan Ivanov
 **/
@Component
@Order(1)
public class FlagAllCloseNetsPattern extends NetAreaAnalysisAbstractPattern{
    public FlagAllCloseNetsPattern(FieldInfo fieldInfo) {
        super(fieldInfo);
    }

    @Override
    protected Turn tryPatternOnNet() {
        if(allClosedCloseNetsAreMined()) {
            return flagAllClosedCloseNets();
        }
        return null;
    }

    private boolean allClosedCloseNetsAreMined() {
        return closedCloseNets.size()+flaggedCloseNets.size() == mineNumber;
    }

    private Turn flagAllClosedCloseNets() {
        TurnOption turnOption = TurnOption.PutFlag;
        List<Net> netsToTurn = new ArrayList<>(closedCloseNets);
        return new Turn(turnOption, netsToTurn);
    }
}
