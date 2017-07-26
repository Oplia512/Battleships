package com.java_academy.logic;

import com.java_academy.logic.model.Ship;
import com.java_academy.logic.model.ShipsType;

import java.util.ArrayList;
import java.util.List;

public enum Players {
    FIRST_PLAYER,
    SECOND_PLAYER;

    private List<Ship> shipList;

    Players() {
        shipList = new ArrayList<>();
        shipList.add(new Ship(ShipsType.ONE_CELL));
        shipList.add(new Ship(ShipsType.ONE_CELL));
        shipList.add(new Ship(ShipsType.ONE_CELL));
        shipList.add(new Ship(ShipsType.ONE_CELL));

        shipList.add(new Ship(ShipsType.TWO_CELLS));
        shipList.add(new Ship(ShipsType.TWO_CELLS));
        shipList.add(new Ship(ShipsType.TWO_CELLS));

        shipList.add(new Ship(ShipsType.THREE_CELLS));
        shipList.add(new Ship(ShipsType.THREE_CELLS));

        shipList.add(new Ship(ShipsType.FOUR_CELLS));
    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
