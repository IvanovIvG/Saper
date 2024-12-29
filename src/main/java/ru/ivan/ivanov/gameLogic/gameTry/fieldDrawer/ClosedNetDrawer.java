package ru.ivan.ivanov.gameLogic.gameTry.fieldDrawer;

import ru.ivan.ivanov.gameLogic.net.Net;
import ru.ivan.ivanov.gameLogic.net.NetState;


public class ClosedNetDrawer implements NetDrawer {
    private static final ClosedNetDrawer instance = new ClosedNetDrawer();
    private ClosedNetDrawer(){}
    public static ClosedNetDrawer getClosedNetDrawer(){
        return instance;
    }

    @Override
    public void printNet(Net net) {
        switch (net.getNetState()) {
            case NetState.Closed -> System.out.print(" ");
            case Flagged -> System.out.print("!");
            case Explosed -> System.out.print("#");
            case Opened -> {
                if(netIsMined(net)){
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
