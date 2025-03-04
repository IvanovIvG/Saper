package ru.ivan.ivanov.gameLogic.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.bot.patterns.Pattern;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;

import java.util.Collections;
import java.util.List;

/**
 * Game bot main class.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class Bot {
    private final FieldInfo fieldInfo;
    private final List<Pattern> usedLogicPatterns;

    public Turn makeTurn() {
        fieldInfo.update();

        for(Pattern pattern: usedLogicPatterns) {
            Turn turn =  pattern.tryPattern();
            if(turnIsMade(turn))
                return turn;
        }

        return new Turn(TurnOption.PlayerTurn, Collections.emptyList());
    }

    private boolean turnIsMade(Turn turn) {
        return turn != null;
    }
}
