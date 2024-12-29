package ru.ivan.ivanov.gameLogic.gameTry.fieldDrawer;

import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.net.NetState;

public class OpenedNetDrawer implements NetDrawer {
    private static final OpenedNetDrawer instance = new OpenedNetDrawer();
    private OpenedNetDrawer(){}
    public static OpenedNetDrawer getOpenedNetDrawer(){
        return instance;
    }

    @Override
    public void printNet(Net net) {
        switch (net.getNetState()) {
            case NetState.Flagged -> System.out.print("!");
            case Explosed -> System.out.print("#");
            case Opened, Closed -> {
                if (netIsMined(net)){
                    System.out.print("*");
                }
                else {
                    System.out.print(net.closeMines);
                }
            }
        }
        System.out.print("|");
    }

    private boolean netIsMined(Net net) {
        return net.isMined();
    }
}
