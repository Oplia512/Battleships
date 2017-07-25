package com.java_academy.logic;

public class WinnerChecker {

    private static final int WIN_COUNT = 3;
    private static int counter = 0;

    public static boolean ifShipsAreAlive(){
        if (counter == WIN_COUNT){
            return false;
        }
        counter ++;
        return true;
    }
}
