package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.Turn;

public class OpenAllCloseNetsPattern extends NetAreaAnalysisPattern {

    public OpenAllCloseNetsPattern(FieldInfo fieldInfo, Turn turnToMake) {
        super(fieldInfo, turnToMake);
    }

    @Override
    protected void tryPatternOnNet() {
        if(allCloseMinesAreFlagged()) {
            openAllCloseClosedNets();
        }
    }

    private boolean allCloseMinesAreFlagged() {
        return flaggedCloseNets.size() == mineNumber;
    }

    private void openAllCloseClosedNets() {
        turnToMake.turnOption = Turn.OpenNet;
        turnToMake.netsToTurn.addAll(closedCloseNets);
    }
}


