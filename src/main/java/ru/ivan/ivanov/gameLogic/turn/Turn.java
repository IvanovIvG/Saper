package ru.ivan.ivanov.gameLogic.turn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.net.Net;

@Component
public class Turn {
    //what is done to net
    public TurnOption turnOption;
    //what nets are changed
    public List<Net> netsToTurn;

    public Turn(){
        turnOption = TurnOption.NoTurn;
        netsToTurn = new ArrayList<>();
    }

    public void clear() {
        this.netsToTurn.clear();
        this.turnOption = TurnOption.NoTurn;
    }
}
