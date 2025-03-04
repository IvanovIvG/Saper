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
     * List of closed nets in field
     */
    private final List<Net> closedNets = new ArrayList<>();


    public void update() {
        gameField = gameData.getGameField();
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
        return net.getCloseNets().stream().filter(Net::isClosed).count();
    }
}
