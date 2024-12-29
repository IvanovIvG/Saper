package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;

public class FlagAllCloseNetsPattern extends NetAreaAnalysisPattern {

    public FlagAllCloseNetsPattern(FieldInfo fieldInfo, Turn turnToMake) {
        super(fieldInfo, turnToMake);
    }

    @Override
    protected void tryPatternOnNet() {
        if(allClosedCloseNetsAreMined()) {
            flaggAllClosedCloseNets();
        }
    }

    private boolean allClosedCloseNetsAreMined() {
        return closedCloseNets.size()+flaggedCloseNets.size() == mineNumber;
    }

    private void flaggAllClosedCloseNets() {
        turnToMake.turnOption = TurnOption.PutFlag;
        turnToMake.netsToTurn.addAll(closedCloseNets);
    }
}



