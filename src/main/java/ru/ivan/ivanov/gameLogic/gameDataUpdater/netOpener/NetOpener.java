package ru.ivan.ivanov.gameLogic.gameDataUpdater.netOpener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.ivanov.gameData.net.Net;
import ru.ivan.ivanov.gameData.net.NetState;

/**
 * Opens net
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class NetOpener {
    private final WaveNetOpener waveNetOpener;

    public void openNet(Net netToOpen) {
        netToOpen.setNetState(NetState.Opened);
        if(shouldOpenCloseNets(netToOpen)) {
            waveNetOpener.waveOpen(netToOpen);
        }
    }

    private boolean shouldOpenCloseNets(Net netToOpen) {
        return netToOpen.getCloseMinesNumber() == 0;
    }

}
