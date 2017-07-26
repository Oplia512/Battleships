package com.java_academy.logic.model;

import java.util.Collections;
import java.util.List;

public class Ship {

    private List<Integer> shipPosition;
    private ShipsType type;

    public Ship(ShipsType type) {
        this.type = type;
        this.shipPosition = Collections.emptyList();
    }

    public Ship setShipPosition(List<Integer> shipPosition) {
        this.shipPosition = shipPosition;
        return this;
    }

    public List<Integer> getShipPosition() {
        return shipPosition;
    }

    public ShipsType getType() {
        return type;
    }
}
