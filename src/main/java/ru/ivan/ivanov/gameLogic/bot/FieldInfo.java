package ru.ivan.ivanov.gameLogic.bot;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.net.Net;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldInfo {
    public List<Net> gameField;
    //list of open nets in field who have at least one closed closeNet
    public List<Net> boardNets;
    //list of closed nets in field
    public List<Net> closedNets;

    FieldInfo(List<Net> gameField){
        this.gameField = gameField;
        boardNets = new ArrayList<>();
        closedNets = new ArrayList<>();
    }

    public void update() {
        fillInBoardNets();
        fillInClosedNets();
    }

    private void fillInBoardNets() {
        boardNets.clear();
        for(Net net: gameField) {
            if(netIsBoard(net)) {
                boardNets.add(net);
            }
        }
    }

    private void fillInClosedNets() {
        closedNets.clear();
        for(Net net: gameField) {
            if(net.isClosed()) {
                closedNets.add(net);
            }
        }
    }

    private boolean netIsBoard(Net net) {
        return net.isOpened() && netHasClosedCloseNets(net);
    }

    private boolean netHasClosedCloseNets(Net net) {
        return numberOfClosedCloseNets(net) != 0;
    }

    private long numberOfClosedCloseNets(Net net) {
        return net.closeNets.stream().filter(Net::isClosed).count();
    }

}
