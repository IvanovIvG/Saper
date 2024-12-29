package ru.ivan.ivanov.gameLogic.bot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.bot.patterns.NoOpenGreyNetsPattern;
import ru.ivan.ivanov.gameLogic.bot.patterns.Pattern;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.OpenAllCloseNetsPattern;
import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.TryConfig;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.CloseNetsInclusionPattern;
import ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis.FlagAllCloseNetsPattern;


//TODO
//Test API
//First ru.ivan.ivanov.menuLogic.gameLogic.turn.turn pattern
//intersection patter
//random net to open pattern
//counting nets
//only one variant analyze
//Unit tests
//Doc
public class Bot {
    private final Turn turnToMake;
    private List<Pattern> usedLogicPatterns;
    private final FieldInfo fieldInfo;

    public Bot(Turn turn, TryConfig tryConfig) {
        turnToMake = turn;
        fieldInfo = new FieldInfo(tryConfig.gameField);
        usedLogicPatterns = getPatterns();
    }

    private List<Pattern> getPatterns() {
        NoOpenGreyNetsPattern noOpenGreyNetsPattern = new NoOpenGreyNetsPattern(fieldInfo, turnToMake);
        FlagAllCloseNetsPattern flagAllCloseNetsPattern = new FlagAllCloseNetsPattern(fieldInfo, turnToMake);
        OpenAllCloseNetsPattern openAllCloseNetsPattern = new OpenAllCloseNetsPattern(fieldInfo, turnToMake);
        CloseNetsInclusionPattern closeNetsInclusionPattern = new CloseNetsInclusionPattern(fieldInfo, turnToMake,
                openAllCloseNetsPattern, flagAllCloseNetsPattern );

        usedLogicPatterns = new ArrayList<>(0);
        usedLogicPatterns.add(noOpenGreyNetsPattern);
        usedLogicPatterns.add(flagAllCloseNetsPattern);
        usedLogicPatterns.add(openAllCloseNetsPattern);
        usedLogicPatterns.add(closeNetsInclusionPattern);

        return usedLogicPatterns;

    }

    public void makeBotTurn() {
        turnToMake.clear();
        fieldInfo.update();

        tryPatterns();
        if(turnIsNotMade()) {
            makePlayerTurn();
        }
    }

    private void tryPatterns() {
        for(Pattern pattern: usedLogicPatterns) {
            pattern.tryPattern();
            if(turnIsMade()) break;
        }
    }

    private boolean turnIsMade() {
        return !turnIsNotMade();
    }

    private boolean turnIsNotMade() {
        return turnToMake.turnOption == TurnOption.NoTurn;
    }

    private void makePlayerTurn(){
        turnToMake.turnOption = TurnOption.PlayerTurn;
    }


}

