package ru.ivan.ivanov.fileReaderTest;

import org.junit.jupiter.api.Test;
import ru.ivan.ivanov.gameCreator.gameFactory.FileReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivan Ivanov
 **/
class FileReaderTest {
    private final FileReader fileReader = new FileReader();

    @Test
    void fileIsReadCorrectly() {
        String configFileName = "src/test/java/ru/ivan/ivanov/fileReaderTest/gameTestConfig.txt";

        try {
            fileReader.readFile(configFileName);
        } catch (IOException e) {
            fail("file not read");
        }

        assertEquals(3, fileReader.getFieldHeight());
        assertEquals(4, fileReader.getFieldWidth());
        assertEquals(6, fileReader.getMineNumber());
        Boolean[] expectedLineField = {false, true, false, false,
                                       true, false, true, true,
                                       false, false, true, true};
        assertArrayEquals(expectedLineField, fileReader.getLineField());
    }
}