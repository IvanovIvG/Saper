package ru.ivan.ivanov.gameLogic;

import lombok.Getter;
import ru.ivan.ivanov.gameLogic.net.Net;

import java.util.ArrayList;
import java.util.List;

public enum Turn {
    NoTurn, PlayerTurn, OpenNet, PutFlag, TakeOffFlag;

    private final List<Net> netsToTurn = new ArrayList<>();

    public void makeTurn(List<Net> netsToTurn){
        this.netsToTurn.clear();
        this.netsToTurn.addAll(netsToTurn);
    }

    public List<Net> applyTurn(){
        List<Net>  new ArrayList<>(netsToTurn);
    }
}

