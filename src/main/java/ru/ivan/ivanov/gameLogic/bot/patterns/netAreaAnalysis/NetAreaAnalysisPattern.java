package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.bot.patterns.Pattern;
import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class NetAreaAnalysisPattern extends Pattern {

    //this fields contains info about closeNets and mines of some net
    protected List<Net> closedCloseNets;
    protected List<Net> flaggedCloseNets;
    protected List<Net> openedCloseNets;
    protected int mineNumber;

    protected NetAreaAnalysisPattern(FieldInfo fieldInfo, Turn turnToMake) {
        super(fieldInfo, turnToMake);
        closedCloseNets = new ArrayList<>(8);
        flaggedCloseNets = new ArrayList<>(8);
        openedCloseNets = new ArrayList<>(8);
    }

    @Override
    public void tryPattern() {
        for(Net net: fieldInfo.boardNets) {
            fillInInfoLists(net);
            tryPatternOnNet();
            if(turnIsMade()) return;
        }
    }

    //infoOnCloseNets contains list of closed nets and number of mines in them
    protected void tryPatternWithAdditionalInfoOnCloseNets(Net net, NetsWithMines infoOnCloseNets) {
        fillInInfoLists(net, infoOnCloseNets);
        if(patternHasMeanToTry()){
            tryPatternOnNet();
        }
    }

    private void fillInInfoLists(Net net) {
        fillInFlaggedCloseNets(net);
        fillInClosedCloseNets(net);
        fillInOpenedCloseNets(net);
        mineNumber = net.closeMinesNumber;
    }

    private void fillInInfoLists(Net net, NetsWithMines infoOnCloseNets) {
        fillInFlaggedCloseNets(net);
        fillInClosedCloseNets(net);
        closedCloseNets.removeAll(infoOnCloseNets.getNets());
        fillInOpenedCloseNets(net);
        mineNumber = net.closeMinesNumber - infoOnCloseNets.getMinesNumber();
    }

    private boolean patternHasMeanToTry() {
        return !closedCloseNets.isEmpty();
    }

    private void fillInOpenedCloseNets(Net net) {
        openedCloseNets.clear();
        openedCloseNets = net.closeNets.stream().filter(Net::isOpened).collect(Collectors.toList());
    }

    private void fillInFlaggedCloseNets(Net net) {
        flaggedCloseNets.clear();
        flaggedCloseNets = net.closeNets.stream().filter(Net::isFlagged).collect(Collectors.toList());
    }

    private void fillInClosedCloseNets(Net net) {
        closedCloseNets.clear();
        closedCloseNets = net.closeNets.stream().filter(Net::isClosed).collect(Collectors.toList());
    }

    protected abstract void tryPatternOnNet();

    private boolean turnIsMade() {
        return turnToMake.turnOption != Turn.NoTurn;
    }
}
