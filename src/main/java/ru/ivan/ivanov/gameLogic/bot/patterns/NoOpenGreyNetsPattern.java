package ru.ivan.ivanov.gameLogic.bot.patterns;

import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.Turn;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//this pattern open random closed net before grey net was opened
//after this he stops working
//used in beginning of game to open nets, before grey net to be opened
public class NoOpenGreyNetsPattern extends Pattern {
    //grey net is net which has 0 in closeNets field
    private boolean greyNetWasOpened;

    public NoOpenGreyNetsPattern(FieldInfo fieldInfo, Turn turnToMake) {
        super(fieldInfo, turnToMake);
        greyNetWasOpened = false;
    }

    public void tryPattern() {
        if (greyNetWasOpened) {
            return;
        }
        Net randomClosedNet = getRandomNet(fieldInfo.closedNets);
        openNet(randomClosedNet);
        if(netIsGrey(randomClosedNet)){
            greyNetWasOpened = true;
        }
    }

    private Net getRandomNet(List<Net> nets) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, nets.size());
        return nets.get(randomNum);
    }

    private void openNet(Net net) {
        turnToMake.netsToTurn.add(net);
        turnToMake.turnOption = Turn.OpenNet;
    }

    private boolean netIsGrey(Net net) {
        return net.closeMinesNumber == 0;
    }


}


