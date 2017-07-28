package com.java_academy.logic.model;

import java.util.List;

public enum Players {
    FIRST_PLAYER,
    SECOND_PLAYER;

    private List<Ship> shipList;

    private Players() {

    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
