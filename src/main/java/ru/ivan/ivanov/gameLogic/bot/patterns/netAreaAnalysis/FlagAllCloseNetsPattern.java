package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.Turn;

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
        turnToMake.turnOption = Turn.PutFlag;
        turnToMake.netsToTurn.addAll(closedCloseNets);
    }
}



