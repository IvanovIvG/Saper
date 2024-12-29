package ru.ivan.ivanov.gameLogic.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.saperUtils.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;

public class Net {
    // true: mined; false: not mined
    private boolean mine;
    //visual state of net
    private NetState netState;
    // number of mines in nearby nets
    public int closeMines;
    //list of nearby nets
    public List <Net> closeNets;

    private final NetOpener netOpener;

    public Net(){
        this.mine = false;
        netState = NetState.Closed;
        closeMines = -1;
        closeNets = new ArrayList<>();
        netOpener = ApplicationContextHolder.getApplicationContext().getBean(NetOpener.class);
    }

    public Net(boolean mined){
        this.mine = mined;
        netState = NetState.Closed;
        closeMines = -1;
        closeNets = new ArrayList<>();
        netOpener = ApplicationContextHolder.getApplicationContext().getBean(NetOpener.class);
    }

    //can open close nets
    public void openNet(List<Net> field) {
        netState = NetState.Opened;
        if(shouldOpenCloseNets(this)) {
            netOpener.waveOpen(this);
        }
    }

    private boolean shouldOpenCloseNets(Net net) {
        return net.closeMines == 0;
    }

    public boolean isMined() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isClosed(){
        return this.netState == NetState.Closed;
    }

    public boolean isOpened(){
        return this.netState == NetState.Opened;
    }

    public boolean isFlagged(){
        return this.netState == NetState.Flagged;
    }

    public boolean isExplosed(){
        return this.netState == NetState.Explosed;
    }

    public NetState getNetState() {
        return netState;
    }

    public void open(){
        netState = NetState.Opened;
    }

    public void explose(){
        netState = NetState.Explosed;
    }

    public void flag(){
        netState = NetState.Flagged;
    }

    public void close(){
        netState = NetState.Closed;
    }
}
