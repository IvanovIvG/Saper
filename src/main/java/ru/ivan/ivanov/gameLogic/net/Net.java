package ru.ivan.ivanov.gameLogic.net;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.ivan.ivanov.utils.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Net {
    private final boolean mined;
    //visual state of net
    private NetState netState = NetState.Closed;
    // number of mines in nearby nets
    private int closeMinesNumber = -1;
    //list of nearby nets
    private final List<Net> closeNets = new ArrayList<>();

    private final static NetOpener netOpener =
            ApplicationContextHolder.getApplicationContext().getBean(NetOpener.class);

    public void open() {
        netState = NetState.Opened;
        if(shouldOpenCloseNets()) {
            netOpener.waveOpen(this);
        }
    }

    private boolean shouldOpenCloseNets() {
        return closeMinesNumber == 0;
    }

    public boolean isClosed(){
        return netState == NetState.Closed;
    }

    public boolean isFlagged(){
        return netState == NetState.Flagged;
    }

    public boolean isExplosed(){
        return netState == NetState.Explosed;
    }

    public boolean isOpened(){
        return netState == NetState.Opened;
    }
}

enum NetState {
    Closed, Flagged, Explosed, Opened
}

