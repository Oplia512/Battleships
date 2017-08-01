package com.java_academy.logic.model;

import java.util.List;

public enum Players {
    FIRST_PLAYER(new Player("First", 3)) {
        @Override
        public Players getOpponent() {
            return FIRST_PLAYER;
        }
    },
    SECOND_PLAYER(new Player("Second", 3)) {
        @Override
        public Players getOpponent() {
            return FIRST_PLAYER;
        }
    };

    private List<Ship> shipList;
    private Player player;

    Players(Player player) {
        this.player = player;
    }

    public abstract Players getOpponent();

    public List<Ship> getShipList() {
        return shipList;
    }

    public Player getPlayer() {
        return player;
    }
}
