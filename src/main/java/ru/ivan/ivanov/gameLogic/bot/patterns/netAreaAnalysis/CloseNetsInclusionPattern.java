package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.turn.Turn;

//this pattern look at opened close net(net b) of some net(net a)
//if all closedCloseNets of net a(list a) contains in closedCloseNet of net b(list b)
//that means that list a contains certain number of mines(mineNumber of net a)
//that allows to use openAllCloseNetsPattern and flagAllCloseNetPattern patterns on net b with
//  additional information
//tries openAllCloseNetsPattern and flagAllCloseNetPattern patterns for net b
//these patterns are given information that list a has certain number of mines

public class CloseNetsInclusionPattern extends NetAreaAnalysisPattern {
    private final OpenAllCloseNetsPattern openAllCloseNetsPattern;
    private final FlagAllCloseNetsPattern flagAllCloseNetPattern;

    public CloseNetsInclusionPattern(FieldInfo fieldInfo, Turn turnToMake,
                                     OpenAllCloseNetsPattern openAllCloseNetsPattern,
                                     FlagAllCloseNetsPattern flagAllCloseNetPattern) {
        super(fieldInfo, turnToMake);
        this.openAllCloseNetsPattern = openAllCloseNetsPattern;
        this.flagAllCloseNetPattern = flagAllCloseNetPattern;
    }

    protected void tryPatternOnNet() {
        for(Net openedCloseNet: openedCloseNets) {
            if(netHasInclusionInCloseNet(openedCloseNet)){
                trySimplePatternsOnCloseNet(openedCloseNet);
            }
        }
    }

    private boolean netHasInclusionInCloseNet(Net openedCloseNet) {
        return openedCloseNet.closeNets.containsAll(closedCloseNets);
    }

    private void trySimplePatternsOnCloseNet(Net openedCloseNet) {
        //intersectionArea contains closed nets for which known for sure number of mines in it
        NetsWithMines intersectionArea = getIntersectionArea();
        openAllCloseNetsPattern.tryPatternWithAdditionalInfoOnCloseNets(openedCloseNet, intersectionArea);
        flagAllCloseNetPattern.tryPatternWithAdditionalInfoOnCloseNets(openedCloseNet, intersectionArea);
    }

    private NetsWithMines getIntersectionArea() {
        List<Net> intersectionNets = closedCloseNets;
        int intersectionMineNumber = mineNumber - flaggedCloseNets.size();
        return new NetsWithMines(intersectionNets, intersectionMineNumber);
    }
}


