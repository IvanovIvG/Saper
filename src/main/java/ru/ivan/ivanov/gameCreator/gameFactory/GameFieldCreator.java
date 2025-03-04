package ru.ivan.ivanov.gameCreator.gameFactory;

import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creates game field nets.
 *
 * @author Ivan Ivanov
 **/
@Component
public class GameFieldCreator {
    private int fieldWidth;
    private int fieldHeight;
    private int mineNumber;

    public List<Net> createRandomGameField(int fieldWidth, int fieldHeight, int mineNumber) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.mineNumber = mineNumber;

        Net[][] matrixField = new Net[fieldHeight][fieldWidth];

        createNetsExamples(matrixField);
        SetMinedFieldInNets(matrixField);
        SetCloseMinesNumberFieldInNets(matrixField);
        SetCloseNetsFieldInNets(matrixField);

        List<Net> gameField = new ArrayList<>(fieldHeight * fieldWidth);
        copyNetsFromMatrixToGameField(matrixField, gameField);

        return gameField;
    }

    public List<Net> createGameFieldFromLineField(int fieldWidth, int fieldHeight, Boolean[] lineField){
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        Net[][] matrixField = new Net[fieldHeight][fieldWidth];

        createNetsExamples(matrixField);
        copyLineFieldToMatrix(lineField, matrixField);
        SetCloseMinesNumberFieldInNets(matrixField);
        SetCloseNetsFieldInNets(matrixField);

        List<Net> gameField = new ArrayList<>(fieldHeight * fieldWidth);
        copyNetsFromMatrixToGameField(matrixField, gameField);

        return gameField;
    }

    private void createNetsExamples(Net[][] matrixField) {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                matrixField[i][j] = new Net();
            }
        }
    }

    private void SetMinedFieldInNets(Net[][] matrixField) {
        Boolean[] lineField = new Boolean[fieldWidth * fieldHeight];
        putMinesOnLineField(lineField);
        shuffleLineField(lineField);
        copyLineFieldToMatrix(lineField, matrixField);
    }

    private void putMinesOnLineField(Boolean[] lineField) {
        for (int i = 0; i < mineNumber; i++) {
            lineField[i] = true;
        }
        int fieldSize = fieldWidth * fieldHeight;
        for (int i = mineNumber; i < fieldSize; i++) {
            lineField[i] = false;
        }
    }

    private void shuffleLineField(Boolean[] lineField) {
        int indexOfLastUnShuffledElement = lineField.length-1;

        while(unShuffledPartIsNotEmpty(indexOfLastUnShuffledElement)) {
            int randomIndex = getRandomIndex(indexOfLastUnShuffledElement);

            //swapping two elements
            Boolean a = lineField[randomIndex];
            lineField[randomIndex] = lineField[indexOfLastUnShuffledElement];
            lineField[indexOfLastUnShuffledElement] = a;

            indexOfLastUnShuffledElement--;
        }
    }

    private boolean unShuffledPartIsNotEmpty(int indexOfLastUnShuffledElement) {
        return indexOfLastUnShuffledElement > 0;
    }

    private int getRandomIndex(int index) {
        Random random = new Random();
        return random.nextInt(index + 1); //adds 1 because nextInt() exclusive its bound
    }

    private void copyLineFieldToMatrix(Boolean[] linedField, Net[][] matrixField) {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                boolean isNetMined = linedField[i*fieldWidth + j];
                matrixField[i][j].setMined(isNetMined);
            }
        }
    }

    private void SetCloseMinesNumberFieldInNets(Net[][] matrixField) {
        Net[][] extendedMatrixField = getExtendedMatrixField(matrixField);
        setNumberFieldsInExtendedMatrix(extendedMatrixField);
        copyNumberFieldsFromExtendedMatrix(matrixField, extendedMatrixField);
    }

    private Net[][] getExtendedMatrixField(Net[][] matrixField) {
        int extendedMatrixHeight = fieldHeight + 2;
        int extendedMatrixWidth = fieldWidth + 2;
        Net[][] extendedMatrixField = new Net[extendedMatrixHeight][extendedMatrixWidth];

        fillInFirstLine(extendedMatrixField);
        fillInLastLine(extendedMatrixField);
        fillInLeftColumn(extendedMatrixField);
        fillInRightColumn(extendedMatrixField);
        fillInCentralNets(extendedMatrixField, matrixField);

        return extendedMatrixField;
    }

    private void fillInFirstLine(Net[][] extendedMatrixField) {
        int extendedMatrixWidth = fieldWidth + 2;

        for(int j = 0; j < extendedMatrixWidth; j++) {
            extendedMatrixField[0][j] = new Net(false);
        }
    }

    private void fillInLastLine(Net[][] extendedMatrixField) {
        int extendedMatrixWidth = fieldWidth + 2;
        int extendedMatrixHeight = fieldHeight + 2;

        for(int j = 0; j < extendedMatrixWidth; j++) {
            extendedMatrixField[extendedMatrixHeight-1][j] = new Net(false);
        }
    }

    private void fillInLeftColumn(Net[][] extendedMatrixField) {
        int extendedMatrixHeight = fieldHeight + 2;

        for(int i = 1; i < extendedMatrixHeight-1; i++) {
            extendedMatrixField[i][0] = new Net(false);
        }
    }

    private void fillInRightColumn(Net[][] extendedMatrixField) {
        int extendedMatrixWidth = fieldWidth + 2;
        int extendedMatrixHeight = fieldHeight + 2;

        for(int i = 1; i < extendedMatrixHeight-1; i++) {
            extendedMatrixField[i][extendedMatrixWidth-1] = new Net(false);
        }
    }

    private void fillInCentralNets(Net[][] extendedMatrixField, Net[][] matrixField) {
        int extendedMatrixHeight = fieldHeight + 2;
        int extendedMatrixWidth = fieldWidth + 2;

        for (int i = 1; i < extendedMatrixHeight-1; i++) {
            for (int j = 1; j < extendedMatrixWidth-1; j++) {
                boolean isMined = matrixField[i-1][j-1].isMined();
                extendedMatrixField[i][j] = new Net(isMined);
            }
        }
    }

    private void setNumberFieldsInExtendedMatrix(Net[][] extendedMatrixField) {
        int extendedMatrixWidth = fieldWidth + 2;
        int extendedMatrixHeight = fieldHeight + 2;

        for (int i = 1; i < extendedMatrixHeight-1; i++) {
            for (int j = 1; j < extendedMatrixWidth-1; j++) {
                int minesNumber = 0;
                if(extendedMatrixField[i-1][j-1].isMined()) minesNumber++;
                if(extendedMatrixField[i-1][j].isMined()) minesNumber++;
                if(extendedMatrixField[i-1][j+1].isMined()) minesNumber++;
                if(extendedMatrixField[i][j-1].isMined()) minesNumber++;
                if(extendedMatrixField[i][j+1].isMined()) minesNumber++;
                if(extendedMatrixField[i+1][j-1].isMined()) minesNumber++;
                if(extendedMatrixField[i+1][j].isMined()) minesNumber++;
                if(extendedMatrixField[i+1][j+1].isMined()) minesNumber++;
                extendedMatrixField[i][j].setCloseMinesNumber(minesNumber);
            }
        }
    }

    private void copyNumberFieldsFromExtendedMatrix(Net[][] matrixField, Net[][] extendedMatrixField) {
        for (int i = 0; i <fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                int closeMinesNumber = extendedMatrixField[i + 1][j + 1].getCloseMinesNumber();
                matrixField[i][j].setCloseMinesNumber(closeMinesNumber);
            }
        }
    }

    private void SetCloseNetsFieldInNets(Net[][] matrixField) {
        setUpLeftNet(matrixField);
        setUpRightNet(matrixField);
        setDownLeftNet(matrixField);
        setDownRightNet(matrixField);
        setLeftColumnNets(matrixField);
        setRightColumnNets(matrixField);
        setUpLineNets(matrixField);
        setDownLineNets(matrixField);
        setCentralNets(matrixField);
    }

    private void setUpLeftNet(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            matrixField[0][0].setCloseNets(new ArrayList<>(3));
            matrixField[0][0].getCloseNets().add(matrixField[1][0]);
            matrixField[0][0].getCloseNets().add(matrixField[0][1]);
            matrixField[0][0].getCloseNets().add(matrixField[1][1]);
        }
    }

    private void setUpRightNet(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            matrixField[0][fieldWidth-1].setCloseNets(new ArrayList<>(3));
            matrixField[0][fieldWidth-1].getCloseNets().add(matrixField[0][fieldWidth - 2]);
            matrixField[0][fieldWidth-1].getCloseNets().add(matrixField[1][fieldWidth - 2]);
            matrixField[0][fieldWidth-1].getCloseNets().add(matrixField[1][fieldWidth - 1]);
        }
    }

    private void setDownLeftNet(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            matrixField[fieldHeight-1][0].setCloseNets(new ArrayList<>(3));
            matrixField[fieldHeight-1][0].getCloseNets().add(matrixField[fieldHeight - 2][0]);
            matrixField[fieldHeight-1][0].getCloseNets().add(matrixField[fieldHeight - 2][1]);
            matrixField[fieldHeight-1][0].getCloseNets().add(matrixField[fieldHeight - 1][1]);
        }
    }

    private void setDownRightNet(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            matrixField[fieldHeight-1][fieldWidth-1].setCloseNets(new ArrayList<>(3));
            matrixField[fieldHeight-1][fieldWidth-1].getCloseNets().add(matrixField[fieldHeight - 2][fieldWidth - 2]);
            matrixField[fieldHeight-1][fieldWidth-1].getCloseNets().add(matrixField[fieldHeight - 1][fieldWidth - 2]);
            matrixField[fieldHeight-1][fieldWidth-1].getCloseNets().add(matrixField[fieldHeight - 2][fieldWidth - 1]);
        }
    }

    private void setLeftColumnNets(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            for(int i = 1, j = 0; i < fieldHeight-1; i++) {
                matrixField[i][j].setCloseNets(new ArrayList<>(9));
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j]);
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j + 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i][j + 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j + 1]);
            }
        }
    }

    private void setRightColumnNets(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            for(int i = 1, j = fieldWidth-1; i < fieldHeight-1; i++) {
                matrixField[i][j].setCloseNets(new ArrayList<>(9));
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j]);
                matrixField[i][j].getCloseNets().add(matrixField[i][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j]);
            }
        }
    }

    private void setUpLineNets(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            for(int i = 0, j = 1; j < fieldWidth-1; j++) {
                matrixField[i][j].setCloseNets(new ArrayList<>(9));
                matrixField[i][j].getCloseNets().add(matrixField[i][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i][j + 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j]);
                matrixField[i][j].getCloseNets().add(matrixField[i + 1][j + 1]);
            }
        }
    }

    private void setDownLineNets(Net[][] matrixField) {
        if(fieldHeight>=2 && fieldWidth>=2) {
            for(int i = fieldHeight-1, j = 1; j < fieldWidth-1; j++) {
                matrixField[i][j].setCloseNets(new ArrayList<>(9));
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j]);
                matrixField[i][j].getCloseNets().add(matrixField[i - 1][j + 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i][j - 1]);
                matrixField[i][j].getCloseNets().add(matrixField[i][j + 1]);
            }
        }
    }

    private void setCentralNets(Net[][] matrixField) {
        if(fieldHeight>=3 && fieldWidth>=3) {
            for(int i = 1; i < fieldHeight-1; i++) {
                for(int j = 1; j < fieldWidth-1; j++) {
                    matrixField[i][j].setCloseNets(new ArrayList<>(9));
                    matrixField[i][j].getCloseNets().add(matrixField[i - 1][j - 1]);
                    matrixField[i][j].getCloseNets().add(matrixField[i - 1][j]);
                    matrixField[i][j].getCloseNets().add(matrixField[i - 1][j + 1]);
                    matrixField[i][j].getCloseNets().add(matrixField[i][j - 1]);
                    matrixField[i][j].getCloseNets().add(matrixField[i][j]);
                    matrixField[i][j].getCloseNets().add(matrixField[i][j + 1]);
                    matrixField[i][j].getCloseNets().add(matrixField[i + 1][j - 1]);
                    matrixField[i][j].getCloseNets().add(matrixField[i + 1][j]);
                    matrixField[i][j].getCloseNets().add(matrixField[i + 1][j + 1]);
                }
            }
        }
    }

    private void copyNetsFromMatrixToGameField(Net[][] matrixField, List<Net> gameField) {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                gameField.add(matrixField[i][j]);
            }
        }
    }
}
