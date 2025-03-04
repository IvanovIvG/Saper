package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import lombok.RequiredArgsConstructor;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.bot.patterns.Pattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ivan Ivanov
 **/
@RequiredArgsConstructor
public abstract class NetAreaAnalysisAbstractPattern implements Pattern {
    //this fields contains info about closeNets and mines of some net
    protected List<Net> closedCloseNets = new ArrayList<>();
    protected List<Net> flaggedCloseNets = new ArrayList<>();
    protected List<Net> openedCloseNets = new ArrayList<>();
    protected int mineNumber;

    private final FieldInfo fieldInfo;

    @Override
    public Turn tryPattern() {
        for(Net net: fieldInfo.getBoardNets()) {
            fillInInfoLists(net);
            Turn turn = tryPatternOnNet();
            if(turnIsMade(turn)) return turn;
        }
        return null;
    }

//    //infoOnCloseNets contains list of closed nets and number of mines in them
//    protected void tryPatternWithAdditionalInfoOnCloseNets(Net net, NetsWithMines infoOnCloseNets) {
//        fillInInfoLists(net, infoOnCloseNets);
//        if(patternHasMeanToTry()){
//            tryPatternOnNet();
//        }
//    }

    private void fillInInfoLists(Net net) {
        fillInFlaggedCloseNets(net);
        fillInClosedCloseNets(net);
        fillInOpenedCloseNets(net);
        mineNumber = net.getCloseMinesNumber();
    }

    private void fillInInfoLists(Net net, NetsWithMines infoOnCloseNets) {
        fillInFlaggedCloseNets(net);
        fillInClosedCloseNets(net);
        closedCloseNets.removeAll(infoOnCloseNets.getNets());
        fillInOpenedCloseNets(net);
        mineNumber = net.getCloseMinesNumber() - infoOnCloseNets.getMinesNumber();
    }

    private boolean patternHasMeanToTry() {
        return !closedCloseNets.isEmpty();
    }

    private void fillInOpenedCloseNets(Net net) {
        openedCloseNets.clear();
        openedCloseNets = net.getCloseNets().stream().filter(Net::isOpened).collect(Collectors.toList());
    }

    private void fillInFlaggedCloseNets(Net net) {
        flaggedCloseNets.clear();
        flaggedCloseNets = net.getCloseNets().stream().filter(Net::isFlagged).collect(Collectors.toList());
    }

    private void fillInClosedCloseNets(Net net) {
        closedCloseNets.clear();
        closedCloseNets = net.getCloseNets().stream().filter(Net::isClosed).collect(Collectors.toList());
    }

    protected abstract Turn tryPatternOnNet();

    private boolean turnIsMade(Turn turn) {
        return turn != null;
    }
}
