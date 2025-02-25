package ru.ivan.ivanov.gameLogic.gameTry.fieldDrawer;

import ru.ivan.ivanov.gameLogic.net.Net;


public class ClosedNetDrawer implements NetDrawer {
    private static final ClosedNetDrawer instance = new ClosedNetDrawer();
    private ClosedNetDrawer(){}
    public static ClosedNetDrawer getClosedNetDrawer(){
        return instance;
    }

    @Override
    public void printNet(Net net) {
        switch (net.getNetState()) {
            case Closed -> System.out.print(" ");
            case Flagged -> System.out.print("!");
            case Explosed -> System.out.print("#");
            case Opened -> {
                if(netIsMined(net)){
                    System.out.print("*");
                }
                else {
                    System.out.print(net.closeMinesNumber);
                }
            }
        }
        System.out.print("|");
    }

    private boolean netIsMined(Net net) {
        return net.isMined();
    }
}
