package ru.ivan.ivanov.gameLogic.turn;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.List;

/**
 * Information about turn to be done
 *
 * @author Ivan Ivanov
 **/
@Data
@RequiredArgsConstructor
public class Turn {
    //what is done to nets
    private final TurnOption turnOption;
    //what nets are changed
    private final List<Net> netsToTurn;
}
