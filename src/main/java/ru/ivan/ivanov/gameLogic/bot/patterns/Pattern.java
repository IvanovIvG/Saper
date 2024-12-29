package ru.ivan.ivanov.gameLogic.bot.patterns;

import ru.ivan.ivanov.gameLogic.bot.FieldInfo;
import ru.ivan.ivanov.gameLogic.turn.Turn;

public abstract class Pattern {
    protected final FieldInfo fieldInfo;
    protected final Turn turnToMake;

    protected Pattern(FieldInfo fieldInfo, Turn turnToMake) {
        this.fieldInfo = fieldInfo;
        this.turnToMake = turnToMake;
    }

    public abstract void tryPattern();
}
