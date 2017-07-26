package com.java_academy.logic.AttackLogic;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
@SuppressWarnings("Duplicates")
public class SafeZoneLogic {

    private int shipLength = 3;

    private final int BOARD_LENGTH = 10;

    public void createSafeZoneHorizontaly(int position) {
        int startPosition = position - 11;

        for (int j = startPosition; j < position + 11; j += BOARD_LENGTH) {
            for (int i = j; i < j + shipLength + 2; ++i) {
                if (verifyPosition(i, position)) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    public void createSafeZoneVerticly(int position) {
        int startPosition = position - 11;
        for (int i = startPosition; i < (shipLength + 3) * BOARD_LENGTH; i += BOARD_LENGTH) {
            for (int j = i; j < 3 + i; ++j){
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }

    public boolean verifyPosition(int i, int position) {

        int MAX_POSITION = 99;
        int MIN_POSITION = 0;

        if (i < MIN_POSITION || i > MAX_POSITION)
            return false;
        else if ((position + 1) % BOARD_LENGTH == 0 && i % BOARD_LENGTH == 0)
            return false;
        else if (i % BOARD_LENGTH == 9)
            return false;
        return true;
    }

}
