package com.java_academy.logic.AttackLogic;

import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
@SuppressWarnings("Duplicates")
public class SafeZoneCreator {


    public static void create(Ship ship, PlayerBoard board) {
        switch (ship.getDirection()) {
            case HORIZONTAL:
                createSafeZoneHorizontaly(ship.getStartCell(), ship.getSize(), board);
                break;
            case VERTICAL:
                createSafeZoneVerticly(ship.getStartCell(), ship.getSize(), board);
                break;
        }
    }

    private static void createSafeZoneHorizontaly(int position, int shipLength, PlayerBoard board) {
        int startPosition = position - 11;
        for (int j = startPosition; j < position + 11; j += board.getBoardSize()) {
            for (int i = j; i < j + shipLength + 2; ++i) {
                if (verifyPosition(i, position, board)) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    private static void createSafeZoneVerticly(int position, int shipLength, PlayerBoard board) {
        int startPosition = position - 11;
        for (int i = startPosition; i < (shipLength + 3) * board.getBoardSize(); i += board.getBoardSize()) {
            for (int j = i; j < 3 + i; ++j) {
                if (verifyPosition(j, position, board)) {
                    System.out.print(j + " ");
                }
            }
            System.out.println("");
        }
    }

    private static boolean verifyPosition(int i, int position, PlayerBoard board) {

        int MAX_POSITION = board.getBoardSize() * board.getBoardSize() - 1;
        int MIN_POSITION = 0;

        if (i < MIN_POSITION || i > MAX_POSITION)
            return false;
        else if ((position + 1) % board.getBoardSize() == 0 && i % board.getBoardSize() == 0)
            return false;
        else if (i % board.getBoardSize() == 9)
            return false;
        return true;
    }

}
