package ru.ivan.ivanov.botTests.testContextCreator;

import ru.ivan.ivanov.gameCreator.GameCreator;
import ru.ivan.ivanov.gameCreator.gameConfig.GameConfig;
import ru.ivan.ivanov.gameCreator.gameConfig.PreGeneratedFieldGameConfig;
import ru.ivan.ivanov.gameData.GameData;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameData.net.NetState;
import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;
import ru.ivan.ivanov.gameLogic.turn.TurnOption;
import ru.ivan.ivanov.utils.ApplicationContextHolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates data needed for patterns test
 * <p>
 * filedInfo keeps data necessary for logic patterns. expectedTurn keeps turn patter is supposed to do.
 * Before getting this field createContext must be called.
 *
 * @author Ivan Ivanov
 **/
public class TestContextCreator {
    private boolean dataCreated;

    private final FieldInfo fieldInfo;
    private Turn expectedTurn;

    private final GameCreator gameCreator;
    private final GameData gameData;


    public TestContextCreator() {
        gameCreator = ApplicationContextHolder.getApplicationContext().getBean(GameCreator.class);
        gameData = ApplicationContextHolder.getApplicationContext().getBean(GameData.class);
        fieldInfo = ApplicationContextHolder.getApplicationContext().getBean(FieldInfo.class);
        dataCreated = false;
    }

    public void createContext(String contextConfigFileName){
        createGame(contextConfigFileName);
        updateGame(contextConfigFileName);
        expectedTurn = createExpectedTurn(contextConfigFileName);
        fieldInfo.update();
        dataCreated = true;
    }

    private void createGame(String contextConfigFileName) {
        GameConfig gameConfig = getGameConfigFromContextConfigFile(contextConfigFileName);
        gameCreator.configure(gameConfig);
        gameCreator.createGame();
    }

    private GameConfig getGameConfigFromContextConfigFile(String contextConfigFileName) {
        Path contextConfigPath = Paths.get(contextConfigFileName);
        String gameConfigFileName = "src/test/java/ru/ivan/ivanov/botTests/testContextCreator/testGameConfig.txt";
        Path gameConfigPath = Paths.get(gameConfigFileName);

        List<String> contextConfigField;
        List<String> gameConfigField = new ArrayList<>();
        try {
            new PrintWriter(gameConfigFileName).close();
            contextConfigField = Files.readAllLines(contextConfigPath, StandardCharsets.UTF_8);
            contextConfigField.removeLast();
            for (String line : contextConfigField){
                line = line.replaceAll("_", "*");
                line = line.replaceAll("!", "*");
                line = line.replaceAll("\\d", " ");
                gameConfigField.add(line);
            }
            Files.write(gameConfigPath, gameConfigField, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return new PreGeneratedFieldGameConfig(gameConfigFileName);
    }

    private void updateGame(String contextConfigFileName) {
        List<NetState> netStates = getNetsStatesFromContextConfig(contextConfigFileName);
        for (int i = 0; i < netStates.size(); i++) {
            Net net = gameData.getGameField().get(i);
            NetState netState = netStates.get(i);
            net.setNetState(netState);
        }
    }

    private List<NetState> getNetsStatesFromContextConfig(String contextConfigFileName) {
        Path contextConfigPath = Paths.get(contextConfigFileName);
        List<String> contextConfigField;
        List<NetState> netsStates = new ArrayList<>();
        try {
            contextConfigField = Files.readAllLines(contextConfigPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        contextConfigField.removeLast();
        for (String line : contextConfigField){
            line = line.replaceAll("\\d", "n");
            line = line.replaceAll("_", " ");
            processLine(line, netsStates);
        }
        return netsStates;
    }

    private void processLine(String line, List<NetState> netsStates) {
        String[] nets = line.split("\\|");
        processNets(nets, netsStates);
    }

    private void processNets(String[] nets, List<NetState> netsStates) {
        for(String net: nets) {
            processNet(net, netsStates);
        }
    }

    private void processNet(String net, List<NetState> netsStates) {
        switch (net) {
            case " " -> netsStates.add(NetState.Closed);
            case "!" -> netsStates.add(NetState.Flagged);
            case "n" -> netsStates.add(NetState.Opened);
            default -> throw new IllegalArgumentException("Unexpected value: " + net);
        }
    }

    private Turn createExpectedTurn(String contextConfigFileName) {
        Path contextConfigPath = Paths.get(contextConfigFileName);
        String[] expectedTurnData;
        try {
            expectedTurnData = Files.readAllLines(contextConfigPath, StandardCharsets.UTF_8).
                    getLast().split(" ");

        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        TurnOption turnOption;
        List<Net> netsToTurn = new ArrayList<>();
        switch (expectedTurnData[0]) {
            case "o" -> turnOption = TurnOption.OpenNet;
            case "f" -> turnOption = TurnOption.PutFlag;
            default -> throw new IllegalArgumentException("Unexpected value: " + expectedTurnData[0]);
        }
        for(int i = 1; i < expectedTurnData.length; i++){
            int netIndex = Integer.parseInt(expectedTurnData[i]);
            Net netToTurn = gameData.getGameField().get(netIndex);
            netsToTurn.add(netToTurn);
        }
        return new Turn(turnOption, netsToTurn);
    }

    public FieldInfo getFiledInfo(){
        if(!dataCreated) throw new RuntimeException("Data hasn't been created!");
        return fieldInfo;
    }

    public Turn getExpectedTurn(){
        if(!dataCreated) throw new RuntimeException("Data hasn't been created!");
        return expectedTurn;
    }
}
