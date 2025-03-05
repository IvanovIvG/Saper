package ru.ivan.ivanov.gameLogic.bot.patterns;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Open random closed net before grey net was opened
 * <p>
 * After this he stops working
 * Used in beginning of game to open nets, before grey net to be opened
 *
 * @author Ivan Ivanov
 **/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class NoOpenGreyNetsPattern implements Pattern {
    private final FieldInfo fieldInfo;

    /**
     * Grey net is net which has 0 in closeNets field
     */
    private boolean greyNetWasOpened = false;

    @Override
    public Turn tryPattern() {
        if (greyNetWasOpened) {
            return null;
        }

        Net randomClosedNet = getRandomNet(fieldInfo.getClosedNets());
        if(netIsGrey(randomClosedNet)){
            greyNetWasOpened = true;
        }
        return new Turn(TurnOption.OpenNet, List.of(randomClosedNet));
    }

    private Net getRandomNet(List<Net> nets) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, nets.size());
        return nets.get(randomNum);
    }

    private boolean netIsGrey(Net net) {
        return net.getCloseMinesNumber() == 0;
    }
}





