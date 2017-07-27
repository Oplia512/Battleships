package com.java_academy.logic.AttackLogic;

import com.java_academy.logic.model.Cell;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
@SuppressWarnings("Duplicates")
public class SafeZoneCreator {

    private final int SHIFT_START_POSITION = 11;
    private final int HORIZONTAL_BUFFER = 2;
    private final int VERTICAL_BUFFER = 3;

    private PlayerBoard playerBoard;
    private final int boardSize;

    public SafeZoneCreator(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
        this.boardSize = playerBoard.getBoardSize();
    }



    private boolean createSafeZoneHorizontaly(int startShipPosition, int shipLength) {
        List<Integer> buffer = new ArrayList<>();
        int startPosition = startShipPosition - SHIFT_START_POSITION;
        int endPosition = startShipPosition + SHIFT_START_POSITION;

        for (int i = startPosition; i < endPosition; i += boardSize) {
            for (int j = i; j < i + shipLength + HORIZONTAL_BUFFER; ++j) {
                if ((verifyPosition(j, startShipPosition))){
                    if (isFree(j)){
                        buffer.add(j);
                    } else {
                        return false;
                    }
                }
            }
        }
        fillBoardWithBuffer(buffer);
        return true;
    }

    private boolean createSafeZoneVerticly(int startShipPosition, int shipLength) {
        List<Integer> buffer = new ArrayList<>();
        int startPosition = startShipPosition - SHIFT_START_POSITION;
        int endPosition = startShipPosition + (shipLength * boardSize) + 1;

        for (int i = startPosition; i < endPosition; i += boardSize) {
            for (int j = i; j < i + VERTICAL_BUFFER; ++j) {
                if ((verifyPosition(j, startShipPosition))){
                    if (isFree(j)){
                        buffer.add(j);
                    } else {
                        return false;
                    }
                }
            }
        }

        fillBoardWithBuffer(buffer);
        return true;
    }

    private void fillBoardWithBuffer(List<Integer> buffer) {
        for (Integer index : buffer) {
            playerBoard.getBoard().put(index, Cell.UNAVAILABLE);
        }
    }

    private boolean isFree(int position) {
        return playerBoard.getCellAtPosition(position).equals(Cell.FREE);
    }


    private boolean verifyPosition(int i, int position) {
        int MAX_POSITION = boardSize * boardSize - 1;
        int MIN_POSITION = 0;
        if (i < MIN_POSITION || i > MAX_POSITION)
            return false;
        else if ((position + 1) % boardSize == 0 && i % boardSize == 0)
            return false;
        else if (i % boardSize == 9 && position % 10 != 9)
            return false;
        return true;
    }

}
