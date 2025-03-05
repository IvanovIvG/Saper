package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;

import java.util.List;

/**
 * Pattern look at opened close net(net b) of some net(net a)
 * if all closedCloseNets of net a(list a) contains in closedCloseNet of net b(list b)
 * that means that list a contains certain number of mines(mineNumber of net a)
 * that allows to use openAllCloseNetsPattern and flagAllCloseNetPattern patterns on net b with
 * additional information
 * tries openAllCloseNetsPattern and flagAllCloseNetPattern patterns for net b
 * these patterns are given information that list a has certain number of mines
 *
 * @author Ivan Ivanov
 **/
@Component
@Order(2)
public class CloseNetsInclusionPattern extends NetAreaAnalysisAbstractPattern{
    private final OpenAllCloseNetsPattern openAllCloseNetsPattern;
    private final FlagAllCloseNetsPattern flagAllCloseNetPattern;

    public CloseNetsInclusionPattern(FieldInfo fieldInfo, OpenAllCloseNetsPattern openAllCloseNetsPattern, FlagAllCloseNetsPattern flagAllCloseNetPattern) {
        super(fieldInfo);
        this.openAllCloseNetsPattern = openAllCloseNetsPattern;
        this.flagAllCloseNetPattern = flagAllCloseNetPattern;
    }


    protected Turn tryPatternOnNet() {
        for(Net openedCloseNet: openedCloseNets) {
            if(netHasInclusionInCloseNet(openedCloseNet)){
                return trySimplePatternsOnCloseNet(openedCloseNet);
            }
        }
        return null;
    }

    private boolean netHasInclusionInCloseNet(Net openedCloseNet) {
        return openedCloseNet.getCloseNets().containsAll(closedCloseNets);
    }

    private Turn trySimplePatternsOnCloseNet(Net openedCloseNet) {
        //intersectionArea contains closed nets for which known for sure number of mines in it
        NetsWithMines intersectionArea = getIntersectionArea();
        Turn turn = openAllCloseNetsPattern.tryPatternWithAdditionalInfoOnCloseNets(openedCloseNet, intersectionArea);
        if(turnIsMade(turn)) return turn;
        turn = flagAllCloseNetPattern.tryPatternWithAdditionalInfoOnCloseNets(openedCloseNet, intersectionArea);
        if(turnIsMade(turn)) return turn;
        return null;
    }

    private NetsWithMines getIntersectionArea() {
        List<Net> intersectionNets = closedCloseNets;
        int intersectionMineNumber = mineNumber - flaggedCloseNets.size();
        return new NetsWithMines(intersectionNets, intersectionMineNumber);
    }

    private boolean turnIsMade(Turn turn) {
        return turn != null;
    }
}
