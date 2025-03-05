package ru.ivan.ivanov.gameLogic.bot;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps information about game field used by bot logic.
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
@Data
public class FieldInfo {
    private final GameData gameData;

    private List<Net> gameField = new ArrayList<>();
    /**
     * Open nets in field who have at least one closed closeNet
     */
    private final List<Net> boardNets = new ArrayList<>();

    /**
     * Closed nets which has close open nets
     */
    private final List<Net> outsideNets = new ArrayList<>();

    /**
     * List of closed nets in field
     */
    private final List<Net> closedNets = new ArrayList<>();

    private int mineNumber;


    public void update() {
        gameField = gameData.getGameField();
        mineNumber = gameData.getNumberOfUnflaggedMines();
        fillInBoardNets();
        fillInOutsideNets();
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

    private void fillInOutsideNets() {
        outsideNets.clear();
        for(Net net: gameField) {
            if(netIsOutside(net)) {
                outsideNets.add(net);
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

    private int numberOfClosedCloseNets(Net net) {
        return (int) net.getCloseNets().stream().filter(Net::isClosed).count();
    }

    private boolean netIsOutside(Net net) {
        return net.isClosed() && netHasOpenedCloseNets(net);
    }

    private boolean netHasOpenedCloseNets(Net net) {
        return numberOfOpenedCloseNets(net) != 0;
    }

    private int numberOfOpenedCloseNets(Net net) {
        return (int) net.getCloseNets().stream().filter(Net::isOpened).count();
    }
}
