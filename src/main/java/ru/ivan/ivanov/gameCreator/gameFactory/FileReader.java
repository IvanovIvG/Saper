package ru.ivan.ivanov.gameCreator.gameFactory;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads information from config file.
 * <p>
 * File contains information about mines positions.
 * Class read file and saves info about field size and mines positions.
 * Before getting info via getters, readFile() must be called.
 *
 * @author Ivan Ivanov
 **/
@Component
public class FileReader {
    private int mineNumber;
    private int fieldWidth;
    private int fieldHeight;
    private Boolean[] lineField;

    private boolean fileRead = false;
    private List<String> stringField = new ArrayList<>();
    private int lineFieldIndex;

    public void readFile(String configFileName) throws IOException {
        fileRead = true;
        Path path = Paths.get(configFileName);
        stringField = Files.readAllLines(path, StandardCharsets.UTF_8);

        mineNumber = 0;
        fieldWidth = stringField.getFirst().length()/2;
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
        if(!fileRead) throw new RuntimeException("File has not been read");
        return mineNumber;
    }

    public int getFieldWidth() {
        if(!fileRead) throw new RuntimeException("File has not been read");
        return fieldWidth;
    }

    public int getFieldHeight() {
        if(!fileRead) throw new RuntimeException("File has not been read");
        return fieldHeight;
    }

    public Boolean[] getLineField() {
        if(!fileRead) throw new RuntimeException("File has not been read");
        return lineField;
    }
}
