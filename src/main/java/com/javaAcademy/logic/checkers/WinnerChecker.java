package com.javaAcademy.logic.checkers;

import com.javaAcademy.logic.model.Player;

public class WinnerChecker {

    public static Boolean isWinner(Player player) {
    	return player.hasNoFleet();
    }
}
