package ru.ivan.ivanov.gameLogic.gameTry.tryConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GameFileConfig {
    private int mineNumber;
    private final int fieldWidth;
    private final int fieldHeight;
    private final Boolean[] lineField;

    private final List<String> stringField;
    private int lineFieldIndex;

    public static GameFileConfig getGameFileConfig(String savedFieldFilePath) throws IOException {
        Path path = Paths.get(savedFieldFilePath);
        return new GameFileConfig(path);
    }

    private GameFileConfig(Path path) throws IOException {
        stringField = Files.readAllLines(path, StandardCharsets.UTF_8);
        mineNumber = 0;
        fieldWidth = stringField.get(0).length()/2;
        fieldHeight = stringField.size();

        lineField = new Boolean[fieldWidth * fieldHeight];
        lineFieldIndex = 0;
        fillInLineFieldAndUpdateMineNumber();
    }

    private void fillInLineFieldAndUpdateMineNumber(){
        for(String line: stringField) {
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] nets = line.split("\\|");
        processNets(nets);
    }

    private void processNets(String[] nets) {
        for(String net: nets) {
            processNet(net);
        }
    }

    private void processNet(String net) {
        switch (net) {
            case " " -> {
                lineField[lineFieldIndex] = false;
                lineFieldIndex++;
            }
            case "*" -> {
                lineField[lineFieldIndex] = true;
                lineFieldIndex++;
                mineNumber++;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + net);
        }
    }

    public int getMineNumber() {
        return mineNumber;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public Boolean[] getLineField() {
        return lineField;
    }
}
