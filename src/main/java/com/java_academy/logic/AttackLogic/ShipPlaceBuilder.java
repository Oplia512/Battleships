package com.java_academy.logic.AttackLogic;

import com.java_academy.logic.model.Cell;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Siarhei Shauchenka on 27.07.17.
 */
public class ShipPlaceBuilder {

    private final int MAX_POSITION;
    private final int MIN_POSITION = 0;

    private Map<Integer, Cell> temporaryShipPlace;
    private PlayerBoard playerBoard;
    private Ship ship;
    private int counter;
    private Map<Integer, Cell> availableCells;


    public ShipPlaceBuilder(Ship ship, PlayerBoard playerBoard) {
        this.ship = ship;
        this.playerBoard = playerBoard;
        temporaryShipPlace = new HashMap<>();
        availableCells = new HashMap<>();
        counter = 1;
        MAX_POSITION = playerBoard.getBoardSize() * playerBoard.getBoardSize() -1;
    }

    private boolean checkAvailableCells(int point){
        if (!availableCells.isEmpty() && !availableCells.containsKey(point)){
           return false;
        }
        recalculateAvailablePoints(point);
        return true;
    }

    private void recalculateAvailablePoints(int point){
        verifyAndFill(point -10, point);
        verifyAndFill(point -1, point);
        verifyAndFill(point +1, point);
        verifyAndFill(point +10, point);
    }

    private void verifyAndFill(int checkingPoint, int point){
        if (verifyPosition(checkingPoint, point) && !isShip(point)){
            availableCells.put(checkingPoint, Cell.FREE);
        }
    }

    public boolean tryToPlaceShip(int point, boolean firstPoint){
        if (firstPoint){
            counter = 1;
        }
        if (!checkAndMarkPoint(point)){
            return false;
        }
        if (counter == ship.getSize()){
            temporaryShipPlace.forEach((place, cell) -> playerBoard.getBoard().put(place, cell));
            ship.placeIt();
        }
        counter++;
        return true;
    }

    private boolean checkAndMarkPoint(int point){
        if (!checkAvailableCells(point)){
            return false;
        }
        if (!isAvailableForShip(point)){
          return false;
        }
        temporaryShipPlace.put(point, Cell.SHIP);
        availableCells.remove(point);

        int startPoint = point -11;
        int endPoint = point + 11;

        for (int i = startPoint; i < endPoint; i+=playerBoard.getBoardSize()) {
            for (int j = i; j < i + 3; j++) {
                if (!verifyPosition(j, point) || isShip(j)){
                    continue;
                }
                if (isFree(j) && j != point){
                        temporaryShipPlace.put(j, Cell.UNAVAILABLE);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isFree(int point){
        return playerBoard.getCellAtPosition(point).equals(Cell.FREE) || playerBoard.getCellAtPosition(point).equals(Cell.UNAVAILABLE);
    }

    private boolean isAvailableForShip(int point){
        return playerBoard.getCellAtPosition(point).equals(Cell.FREE);
    }

    private boolean isShip(int point) {
        return temporaryShipPlace.containsKey(point) && temporaryShipPlace.get(point).equals(Cell.SHIP);
    }

    private boolean verifyPosition(int i, int shipPosition) {
        if (i < MIN_POSITION || i > MAX_POSITION)
            return false;
        else if ((shipPosition + 1) % playerBoard.getBoardSize() == 0 && i % playerBoard.getBoardSize() == 0)
            return false;
        else if (i % playerBoard.getBoardSize() == 9 && shipPosition % 10 == 0)
            return false;
        return true;
    }

    public Map<Integer, Cell> getAvailableCells() {
        return availableCells;
    }
}
