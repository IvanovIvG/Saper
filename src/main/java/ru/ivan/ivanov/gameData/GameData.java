package ru.ivan.ivanov.gameData;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.ArrayList;
import java.util.List;

/**
 * Class keeps data about game.
 *
 * @author Ivan Ivanov
 **/
@Component
@Data
public class GameData {
    private List<Net> gameField = new ArrayList<>();
    private GameState gameState;
    private int fieldWidth;
    private int fieldHeight;
    private int mineNumber;
    private int numberOfUnflaggedMines;

    public void unFlagOneNet(){
        numberOfUnflaggedMines++;
    }

    public void flagOneNet(){
        numberOfUnflaggedMines--;
    }
}
