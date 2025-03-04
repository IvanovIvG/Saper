package ru.ivan.ivanov.gameLogic.bot.patterns;

import ru.ivan.ivanov.gameLogic.turn.Turn;

/**
 * Game pattern contract.
 * <p>
 * Each pattern analyze game field and return turn if it sees solution.
 * If there are no solution pattern return null.
 *
 * @author Ivan Ivanov
 **/
public interface Pattern {
    Turn tryPattern();
}
