package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ivan.ivanov.gameData.net.Net;

import java.util.List;

/**
 * List of nets which certainly contains mines
 *
 * @author Ivan Ivanov
 **/
@Data
@AllArgsConstructor
public class NetsWithMines {
    private List<Net> nets;
    private int minesNumber;
}
