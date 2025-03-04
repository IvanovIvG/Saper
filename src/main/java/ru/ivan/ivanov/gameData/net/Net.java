package ru.ivan.ivanov.gameData.net;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *  Representation of game field's net in app.
 *
 *  @author Ivan Ivanov
 **/
@Data
@NoArgsConstructor
public class Net {

    /**
     * Is net mined
     */
    private boolean mined;

    /**
     * Visual state of net
     */
    private NetState netState = NetState.Closed;

    /**
     * Number of mines in nearby nets (min 0, max 8)
     */
    private int closeMinesNumber = -1;

    /**
     * List of nearby nets
     */
    private List<Net> closeNets = new ArrayList<>();

    public Net(boolean mined){
        this.mined = mined;
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

