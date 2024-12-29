package ru.ivan.ivanov.gameLogic.gameTry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.TryConfig;
import ru.ivan.ivanov.gameLogic.bot.Bot;
import ru.ivan.ivanov.gameLogic.gameTry.fieldDrawer.FieldDrawer;
import ru.ivan.ivanov.gameLogic.gameTry.tryConfig.GameState;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import java.io.IOException;

public class GameTry {
    //path to file with saved field in txt file
    private static final String SavedFieldFilePath = "C:\\Users\\01\\Desktop\\Saper_Field.txt";
    public final TryConfig tryConfig;
    private final Turn turnToMake;
    private final Bot bot;

    //create GameTry with random field
    public GameTry(int fieldWidth, int fieldHeight, int mineNumber) {
        tryConfig = TryConfig.getTryConfigWithRandomField(fieldWidth, fieldHeight, mineNumber);
        turnToMake = new Turn();
        bot = new Bot(turnToMake, tryConfig);
    }

    //create GameTry with field from file
    public GameTry() throws IOException {
        tryConfig =  TryConfig.getTryConfigFromFile(SavedFieldFilePath);
        turnToMake = new Turn();
        bot = new Bot(turnToMake, tryConfig);
    }

    public boolean playGameStub() {
        System.out.println("You're in a game");
        return true;
    }

    //main game function
    public boolean playGame() {
        while(gameGoOn()) {
            bot.makeBotTurn();
            TryUpdater.updateTryConfig(turnToMake, tryConfig);
            FieldDrawer.showClosedField(tryConfig);
        }
        return gameResult();
    }

    private boolean gameGoOn() {
        return tryConfig.gameState == GameState.GameGoOn;
    }

    private boolean gameResult() {
        return tryConfig.gameState == GameState.GameWin;
    }

    public static void main(String[] args) throws IOException {
        GameTry game = new GameTry(30, 16, 99);
        boolean gameWin = game.playGame();
        if (gameWin) {
            System.out.println("You win!");
        }
        else{
            System.out.println("You lose!");
        }
    }
}

