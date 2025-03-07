package ru.ivan.ivanov.gameLogic.gameDataUpdater.netOpener;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.ArrayList;
import java.util.List;

/**
 * Open close nets of net.
 *
 * @author Ivan Ivanov
 **/
@Component
public class WaveNetOpener {
    //nets to be open on next iteration of algorithm
    private final List<Net> boardNets = new ArrayList<>();

    public void waveOpen(Net initialNet){
        fillInBoardList(initialNet);
        while(leftNetsToOpen()){
            makeOpenIteration();
        }
    }

    private void makeOpenIteration() {
        openBoardNets();
        updateBoardNets();
    }

    private void fillInBoardList(Net initialNet) {
        boardNets.clear();
        for(Net net : initialNet.getCloseNets()){
            if(netShouldBeOpened(net)) {
                boardNets.add(net);
            }
        }
    }

    private boolean netShouldBeOpened(Net net) {
        return net.isClosed();
    }

    private boolean leftNetsToOpen() {
        return !boardNets.isEmpty();
    }

    private void openBoardNets() {
        for(Net net : boardNets){
            net.open();
        }
    }

    private void updateBoardNets() {
        List<Net> newBoardNets = getNewBoardNets();
        copyNetsToBoardNets(newBoardNets);
    }

    private List<Net> getNewBoardNets() {
        //nets to be open on next iteration
        List<Net> newBoardNets = new ArrayList<>();
        //nets in boardNets list which has 0 in closeMines field
        List<Net> greyBoardNets = getGreyBoardNets();
        for(Net net : greyBoardNets){
            addNetsClosedCloseNetsToNewBoard(net, newBoardNets);
        }
        return newBoardNets;
    }


    private List<Net> getGreyBoardNets() {
        List<Net> greyBoardNets = new ArrayList<>();
        for(Net net : boardNets){
            if (netIsGrey(net)) greyBoardNets.add(net);
        }
        return greyBoardNets;
    }

    private boolean netIsGrey(Net net) {
        return net.getCloseMinesNumber() == 0;
    }

    private void addNetsClosedCloseNetsToNewBoard(Net net, List<Net> newBoardNets) {
        if(hasClosedCloseNets(net)) {
            addHerClosedCloseNetsToNewBoard(net, newBoardNets);
        }
    }

    private boolean hasClosedCloseNets(Net net) {
        for(Net closeNet : net.getCloseNets()){
            if(closeNet.isClosed()) {
                return true;
            }
        }
        return false;
    }

    private void addHerClosedCloseNetsToNewBoard(Net net, List<Net> newBoardNets) {
        for(Net closeNet : net.getCloseNets()){
            if(closeNet.isClosed()) addCloseNet(closeNet, newBoardNets);
        }
    }

    private void addCloseNet(Net closeNet, List<Net> newBoardNets) {
        if(!newBoardNets.contains(closeNet)) {
            newBoardNets.add(closeNet);
        }
    }

    private void copyNetsToBoardNets(List<Net> newBoardNets) {
        boardNets.clear();
        boardNets.addAll(newBoardNets);
        newBoardNets.clear();
    }
}
