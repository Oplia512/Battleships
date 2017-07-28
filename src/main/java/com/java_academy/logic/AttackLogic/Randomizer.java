package com.java_academy.logic.AttackLogic;

import com.java_academy.logic.model.Cell;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;

import java.util.*;

/**
 * Created by Siarhei Shauchenka on 27.07.17.
 */
public class Randomizer {

    private PlayerBoard playerBoard;
    private Random random;


    public Randomizer(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
        this.random = new Random();
    }

    public void setShip(Ship ship){
        ShipPlaceBuilder shipPlaceBuilder = new ShipPlaceBuilder(ship, playerBoard);
        while (!ship.isPlaced()) {
            shipPlaceBuilder.tryToPlaceShip(findFirsPlace(), true);
            for (int i = 1; i < ship.getSize(); i++) {
                int pointToPlace = findPointToPlace(shipPlaceBuilder);
                shipPlaceBuilder.tryToPlaceShip(pointToPlace, false);
            }
        }
    }

    private int findPointToPlace(ShipPlaceBuilder shipPlaceBuilder){
        List<Integer> availablePoints = new ArrayList<>();
        for(Map.Entry<Integer, Cell> entry : shipPlaceBuilder.getAvailableCells().entrySet()) {
            if(!entry.getValue().equals(Cell.SHIP)) {
                availablePoints.add(entry.getKey());
            }
        }
        return availablePoints.get(random.nextInt(availablePoints.size()-1));
    }

    private int findFirsPlace(){
       int nextPoint = random.nextInt(playerBoard.getBoardSize()*playerBoard.getBoardSize()-1);
       if (playerBoard.getCellAtPosition(nextPoint).equals(Cell.FREE)){
           return nextPoint;
       }
       else return findFirsPlace();
    }
}
