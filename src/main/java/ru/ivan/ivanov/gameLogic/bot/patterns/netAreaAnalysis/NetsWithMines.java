package ru.ivan.ivanov.gameLogic.bot.patterns.netAreaAnalysis;

import ru.ivan.ivanov.gameLogic.net.Net;
import java.util.List;

//this class has list of nets which certainly contains minesNumber
public class NetsWithMines {
    private List<Net> nets;
    private int minesNumber;

    public NetsWithMines(List<Net> nets, int minesNumber) {
        this.nets = nets;
        this.minesNumber = minesNumber;
    }

    public List<Net> getNets() {
        return nets;
    }

    public void setNets(List<Net> nets) {
        this.nets = nets;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public void setMinesNumber(int minesNumber) {
        this.minesNumber = minesNumber;
    }
}
