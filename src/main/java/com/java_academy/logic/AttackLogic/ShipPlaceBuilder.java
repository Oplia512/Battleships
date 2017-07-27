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
        counter = 0;
    }

    private boolean checkAvailableCells(int point){
        if (!availableCells.isEmpty() && !availableCells.containsKey(point)){
           return false;
        }
        resetAvailablePoints(point);
        return true;
    }

    private void resetAvailablePoints(int point){
        for (int i = 0; i < 4; i++) {

        }
    }

    public boolean tryToPlaceShip(int point){
        counter++;
        if (!checkAndMarkPoint(point)){
            return false;
        }
        if (counter == ship.getSize()){
            temporaryShipPlace.forEach((place, cell) -> playerBoard.getBoard().put(place, cell));
            ship.placeIt();
        }
        return true;
    }

    private boolean checkAndMarkPoint(int point){
        if (!isAvailableForShip(point)){
          return false;
        }
        temporaryShipPlace.put(point, Cell.SHIP);

        int startPoint = point -11;
        int endPoint = point + 11;

        for (int i = startPoint; i < endPoint; i+=playerBoard.getBoardSize()) {
            for (int j = i; j < i + 3; j++) {
                if (j < 0 || isShip(j)){
                    continue;
                }
                if (isOnBoard(j, point) && isFree(j) && j != point){
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

    private boolean isOnBoard(int point, int shipPoint){
        if (shipPoint%(playerBoard.getBoardSize()-1) == 0 && point%playerBoard.getBoardSize() == 0){
            return false;
        }
        return point >= 0 && point < playerBoard.getBoardSize() * playerBoard.getBoardSize();
    }
}
