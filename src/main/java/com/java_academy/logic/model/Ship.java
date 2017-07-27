package com.java_academy.logic.model;

import java.util.Collections;
import java.util.List;


public class Ship {

    private ShipsType type;
    private boolean isPlaced;

    public Ship(ShipsType type) {
        this.type = type;
        isPlaced = false;
    }


    public List<Integer> getShipPosition() {
      return Collections.emptyList();
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void placeIt() {
        isPlaced = true;
    }

    public int getSize() {
        return type.getSize();
    }

}
