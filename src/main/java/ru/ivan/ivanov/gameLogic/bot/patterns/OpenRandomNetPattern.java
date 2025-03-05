package ru.ivan.ivanov.gameLogic.bot.patterns;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Open random outside net.
 * <p>
 * If there are no such nets, pattern analyse left closed nets and make turn by them.
 * Has the lowest priority. Adds to patterns list last.
 * @author Ivan Ivanov
 **/
@Component
@Order()
@RequiredArgsConstructor
public class OpenRandomNetPattern implements Pattern{
    private final FieldInfo fieldInfo;

    @Override
    public Turn tryPattern() {
        List<Net> outsideNets = fieldInfo.getOutsideNets();
        if(thereAreNoOutsideNets(outsideNets)){
            return makeTurnWithCentralNets();
        }
        return openRandomOutsideNet(outsideNets);
    }

    private boolean thereAreNoOutsideNets(List<Net> outsideNets) {
        return outsideNets.isEmpty();
    }

    private Turn openRandomOutsideNet(List<Net> outsideNets) {
        Net randomOutsideNet = getRandomNet(outsideNets);
        return new Turn(TurnOption.OpenNet, List.of(randomOutsideNet));
    }

    private Turn makeTurnWithCentralNets() {
        List<Net> centralNets = fieldInfo.getClosedNets();
        if(noUnMinedNetsLeft(centralNets)){
            return flagAllLeftClosedNets(centralNets);
        }
        else{
            return openRandomCentralNet(centralNets);
        }
    }

    private boolean noUnMinedNetsLeft(List<Net> centralNets) {
        return centralNets.size() == fieldInfo.getMineNumber();
    }

    private Turn flagAllLeftClosedNets(List<Net> centralNets) {
        return new Turn(TurnOption.PutFlag, centralNets);
    }

    private Turn openRandomCentralNet(List<Net> centralNets) {
        Net randomCentralNet = getRandomNet(centralNets);
        return new Turn(TurnOption.OpenNet, List.of(randomCentralNet));
    }

    private Net getRandomNet(List<Net> nets) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, nets.size());
        return nets.get(randomNum);
    }
}
